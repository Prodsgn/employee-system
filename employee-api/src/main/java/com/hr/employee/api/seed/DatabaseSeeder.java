package com.hr.employee.api.seed;

import com.github.javafaker.Faker;
import com.hr.employee.api.doa.EmployeeRepository;
import com.hr.employee.api.model.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final Faker faker = new Faker();

    public DatabaseSeeder(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Employee employee = new Employee();
            employee.setFirstName(faker.name().firstName());
            employee.setLastName(faker.name().lastName());
            employee.setEmail(faker.internet().emailAddress());
            employees.add(employee);
        }
        employeeRepository.saveAll(employees);
    }
}
