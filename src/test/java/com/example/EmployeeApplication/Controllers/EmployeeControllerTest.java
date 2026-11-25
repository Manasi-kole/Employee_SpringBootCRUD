package com.example.EmployeeApplication.Controllers;

import com.example.EmployeeApplication.Entity.Employee;
import com.example.EmployeeApplication.Exceptions.EmployeeNotFoundException;
import com.example.EmployeeApplication.Exceptions.GlobalExceptionHandler;
import com.example.EmployeeApplication.Services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Tests the controller's URLs and HTTP status codes. MockMvc sends fake requests, no server is started.
@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new EmployeeController(employeeService))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void getEmployee_whenFound_returns200() throws Exception {
        when(employeeService.getEmployee(1))
                .thenReturn(new Employee(1, "Manasi", "K", 50000.0, "manasi@example.com", "IT"));

        mockMvc.perform(get("/Employee/getEmployee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fname").value("Manasi"));
    }

    @Test
    void getEmployee_whenMissing_returns404() throws Exception {
        when(employeeService.getEmployee(99)).thenThrow(new EmployeeNotFoundException(99));

        mockMvc.perform(get("/Employee/getEmployee/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Employee not found with id 99"));
    }

    @Test
    void deleteEmployee_returns204() throws Exception {
        mockMvc.perform(delete("/Employee/deleteEmployee/1"))
                .andExpect(status().isNoContent());

        verify(employeeService).deletebyId(1);
    }
}
