package com.hr.employee.api.mapper;

import com.hr.employee.api.dto.EmployeeDto;
import com.hr.employee.api.model.Employee;
import org.mapstruct.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee, EmployeeDto> {
}
