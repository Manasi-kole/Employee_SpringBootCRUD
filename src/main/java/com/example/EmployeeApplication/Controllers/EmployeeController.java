package com.example.EmployeeApplication.Controllers;

import com.example.EmployeeApplication.Entity.Employee;
import com.example.EmployeeApplication.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController  //@controller and @ResponseBody
@RequestMapping("/Employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee e){
         final Employee savedEmployee = employeeService.addEmployee(e);
         return ResponseEntity.ok(savedEmployee);
    }

    @GetMapping("/getEmployee/{empid}")
    public ResponseEntity<Employee> getEmployeebyEmpId(@PathVariable("empid") Integer empid ){
        final Employee employee = employeeService.getEmployee(empid);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee e){
        final Employee updatedEmployee = employeeService.updateEmployee(e);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/deleteEmployee/{empid}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("empid") Integer empid){
        employeeService.deletebyId(empid);
        return ResponseEntity.noContent().build();   // 204 = deleted, nothing to return
    }
}
