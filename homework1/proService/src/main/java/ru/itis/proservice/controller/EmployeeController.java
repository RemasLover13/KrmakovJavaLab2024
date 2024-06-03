package ru.itis.proservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.proservice.entity.Employee;
import ru.itis.proservice.util.JsonUtils;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {


        @GetMapping
        public List<Employee> getAll() {
            List<Employee> employees = JsonUtils.readEmployeesFromFile();
            return employees;
        }
}
