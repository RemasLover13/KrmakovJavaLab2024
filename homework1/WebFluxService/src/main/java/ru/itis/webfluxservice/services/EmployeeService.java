package ru.itis.webfluxservice.services;

import reactor.core.publisher.Flux;
import ru.itis.webfluxservice.entities.Employee;

public interface EmployeeService {
    Flux<Employee> getAllEmployees();
}
