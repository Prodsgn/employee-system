package com.hr.employee.ui.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class EmployeeDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;
}
