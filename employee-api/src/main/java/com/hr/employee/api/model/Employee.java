package com.hr.employee.api.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "hr_employees")
public class Employee extends BaseEntity<Long>{

    private String firstName;

    private String lastName;

    private String email;
}
