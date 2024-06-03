package ru.itis.newbieservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.newbieservice.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
