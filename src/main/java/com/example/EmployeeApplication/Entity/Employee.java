package com.example.EmployeeApplication.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data  //generates automatically getters, setters, equals, hashcode, toString method.
@NoArgsConstructor //for default constructor
@AllArgsConstructor  //for parameterized contructor
public class Employee {
    @Id
    private Integer empid;
    private String fname;
    private String lname;
    private Double salary;
    private String email;
    private String department;

}
