package com.hr.employee.ui.client;


import com.hr.employee.ui.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name="${service.feign.employee:employee-service}", url = "http://localhost:8081/v1/employee")
public interface EmployeeClientFeign {

    @GetMapping("")
    List<EmployeeDto> findAll();

    @GetMapping("/{id}")
    EmployeeDto findById(@PathVariable Long id);

    @PostMapping("")
    EmployeeDto insert(@RequestBody EmployeeDto dto) ;

    @PutMapping("")
    EmployeeDto update(@RequestBody EmployeeDto dto);

    @DeleteMapping("/{id}")
    ResponseEntity deleteById(@PathVariable Long id);

}
