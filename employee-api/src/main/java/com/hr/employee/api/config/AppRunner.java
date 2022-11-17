package com.hr.employee.api.config;

import com.hr.employee.api.model.Employee;
import com.hr.employee.api.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    private final EmployeeService employeeService;


    @Override
    public void run(String... args) throws Exception {
        employeeDemoData();
    }


    public void employeeDemoData(){
        if(employeeService.findAll().isEmpty()){
            employeeService.insert(Employee
                    .builder()
                            .firstName("Mohamed")
                            .lastName("Omer")
                            .hireDate(LocalDate.now())
                            .salary(10000.0)
                    .build());

            employeeService.insert(Employee
                    .builder()
                    .firstName("Ali")
                    .lastName("Ahmed")
                    .hireDate(LocalDate.now())
                    .salary(10000.0)
                    .build());

            employeeService.insert(Employee
                    .builder()
                    .firstName("Huda")
                    .lastName("Ibrahim")
                    .hireDate(LocalDate.now())
                    .salary(10000.0)
                    .build());
        }
    }

}
