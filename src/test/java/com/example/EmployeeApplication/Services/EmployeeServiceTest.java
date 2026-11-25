package com.example.EmployeeApplication.Services;

import com.example.EmployeeApplication.Entity.Employee;
import com.example.EmployeeApplication.Exceptions.EmployeeNotFoundException;
import com.example.EmployeeApplication.Repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// Unit tests for EmployeeService. The repository is a fake (mock), so no database is used.
@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee sampleEmployee() {
        return new Employee(1, "Manasi", "K", 50000.0, "manasi@example.com", "IT");
    }

    @Test
    void addEmployee_returnsSavedEmployee() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(sampleEmployee());

        Employee saved = employeeService.addEmployee(sampleEmployee());

        assertEquals("Manasi", saved.getFname());
    }

    @Test
    void getEmployee_whenFound_returnsEmployee() {
        when(employeeRepository.findById(1)).thenReturn(Optional.of(sampleEmployee()));

        Employee found = employeeService.getEmployee(1);

        assertEquals(1, found.getEmpid());
    }

    @Test
    void getEmployee_whenMissing_throwsNotFound() {
        when(employeeRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployee(99));
    }

    @Test
    void updateEmployee_whenMissing_throwsNotFound() {
        when(employeeRepository.existsById(1)).thenReturn(false);

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.updateEmployee(sampleEmployee()));
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void deleteEmployee_whenFound_deletes() {
        when(employeeRepository.existsById(1)).thenReturn(true);

        employeeService.deletebyId(1);

        verify(employeeRepository).deleteById(1);
    }
}
