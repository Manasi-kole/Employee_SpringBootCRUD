package com.example.EmployeeApplication.Services;

import com.example.EmployeeApplication.Entity.Employee;
import com.example.EmployeeApplication.Exceptions.EmployeeNotFoundException;
import com.example.EmployeeApplication.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // constructor injection (same style as the controller)
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // findById returns an Optional; if it is empty we throw a "not found" error
    public Employee getEmployee(Integer id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Employee updateEmployee(Employee employee) {
        if (!employeeRepository.existsById(employee.getEmpid())) {
            throw new EmployeeNotFoundException(employee.getEmpid());
        }
        return employeeRepository.save(employee);
    }

    public void deletebyId(Integer empid){
        if (!employeeRepository.existsById(empid)) {
            throw new EmployeeNotFoundException(empid);
        }
        employeeRepository.deleteById(empid);
    }
}
