package ru.itis.newbieservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.newbieservice.entity.Employee;
import ru.itis.newbieservice.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class NewBieController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAll() throws InterruptedException {

        Thread.sleep(3000);

        List<Employee> employees = employeeService.getAll();
        System.out.println(employees);
        return employees;
    }


//    @GetMapping
//    public ResponseEntity<List<Employee>> getAllEmployees() {
//        List<Employee> employees = employeeService.getAll();
//        return ResponseEntity.ok(employees);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        Optional<Employee> employee = employeeService.getById(id);
        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> addEmployee(@RequestBody Employee employee) {
        employeeService.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        if (!employeeService.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        employee.setId(id); // Устанавливаем id сотрудника из пути
        employeeService.update(employee);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        Optional<Employee> employee = employeeService.getById(id);
        if (!employee.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        employeeService.delete(employee.get());
        return ResponseEntity.ok().build();
    }
}
