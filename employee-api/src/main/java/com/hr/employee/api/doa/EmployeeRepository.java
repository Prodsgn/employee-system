package com.hr.employee.api.doa;

import com.hr.employee.api.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee, Long>{
}
