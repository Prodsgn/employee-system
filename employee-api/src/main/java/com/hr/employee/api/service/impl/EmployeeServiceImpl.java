package com.hr.employee.api.service.impl;

import com.hr.employee.api.model.Employee;
import com.hr.employee.api.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee, Long> implements EmployeeService {
}
