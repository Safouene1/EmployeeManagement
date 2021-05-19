package com.safouen.employe_management.controllers;

import com.safouen.employe_management.exception.ResourceNotFoundException;
import com.safouen.employe_management.model.Employee;
import com.safouen.employe_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    // getting all the employees list
    @GetMapping("/employees")
    public List<Employee> getAllEmployes(){
        return employeeRepository.findAll();
    }

    // create employe rest api
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    // get employee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity getEmployeeById(@PathVariable Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist"));
        return ResponseEntity.ok(employee);
    }

    // upddate employee
    @PutMapping("/employees/{id}")
    public ResponseEntity updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist"));
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());
        Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }
}
