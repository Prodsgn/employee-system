package com.hr.employee.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class EmployeeDto {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate hireDate;

    private double salary;
}
