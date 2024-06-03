package ru.itis.newbieservice.service;

import ru.itis.newbieservice.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAll();
    void save(Employee employee);
    void delete(Employee employee);
    void update(Employee employee);
    Optional<Employee> getById(int id);
}
