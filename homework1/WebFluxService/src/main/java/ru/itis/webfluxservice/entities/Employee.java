package ru.itis.webfluxservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Employee {
    private int id;
    private String name;
    private String surname;
    private int age;
    private int salary;
    private String department;
    private String position;
}
