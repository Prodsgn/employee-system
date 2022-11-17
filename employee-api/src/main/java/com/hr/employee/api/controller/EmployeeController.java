package com.hr.employee.api.controller;

import com.hr.employee.api.dto.EmployeeDto;
import com.hr.employee.api.model.Employee;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/employee")
public class EmployeeController extends BaseControllerImpl<Employee, EmployeeDto, Long>{


}
