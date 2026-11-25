package com.example.EmployeeApplication.Repository;

import com.example.EmployeeApplication.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
