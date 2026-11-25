package com.example.EmployeeApplication.Exceptions;

// Our own error type for "this employee id does not exist"
public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Integer empid) {
        super("Employee not found with id " + empid);
    }
}
