package ru.itis.webfluxservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.itis.webfluxservice.entities.Employee;
import ru.itis.webfluxservice.services.impl.EmployeeServiceImpl;

@AllArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeServiceImpl employeeServiceImpl;


    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE + ";charset=UTF-8")
    public Flux<Employee> getAllEmployees() {
        return employeeServiceImpl.getAllEmployees();
    }
}
