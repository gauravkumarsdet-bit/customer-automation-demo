package com.example.customerautomation.controller;

import com.example.customerautomation.entity.Employee;
import com.example.customerautomation.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeApiController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{empId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer empId) {
        try {
            return ResponseEntity.ok(employeeService.getEmployeeById(empId));
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        try {
            return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @PutMapping("/{empId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer empId, @RequestBody Employee employee) {
        try {
            return ResponseEntity.ok(employeeService.updateEmployee(empId, employee));
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer empId) {
        try {
            employeeService.deleteEmployee(empId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }
}
