package com.hr.employee.api.controller;

import com.hr.employee.api.dto.EmployeeDto;
import com.hr.employee.api.mapper.EmployeeMapper;
import com.hr.employee.api.model.Employee;
import com.hr.employee.api.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/employee")
public class EmployeeController extends BaseControllerImpl<Employee, EmployeeDto, Long>{

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;
    
    @GetMapping("")
    @Operation(summary = "Find All", description = "to retrieve list of all data")
    public ResponseEntity<?> findAll() {
        List<EmployeeDto> dtos = employeeMapper.map(employeeService.findAll());
        return ResponseEntity.ok(dtos);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Find by id", description = "to retrieve element by id")
    public ResponseEntity<?> findById(@PathVariable Long id) {
     EmployeeDto dto =  employeeMapper.map(employeeService.findById(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping("")
    @Operation(summary = "Insert", description = "to adding one element by json object")
    public ResponseEntity insert(@Valid @RequestBody EmployeeDto dto) {
        Employee entity = employeeMapper.unMap(dto);
        Employee result = employeeService.insert(entity);
        EmployeeDto dtos = employeeMapper.map(result);
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("")
    @Operation(summary = "Update", description = "to updating one element by json object")
    public ResponseEntity update(@Valid @RequestBody EmployeeDto dto) {
        Employee current = employeeService.getReferenceById(dto.getId());
        Employee entity = employeeMapper.unMap(current, dto);
        Employee result = employeeService.update(entity);
        EmployeeDto dtos = employeeMapper.map(result);
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("")
    @Operation(summary = "Delete", description = "to delete one element by id")
    public ResponseEntity deleteById(@RequestParam("id") Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.ok((null));
    }

}
