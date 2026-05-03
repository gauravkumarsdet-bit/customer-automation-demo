package com.example.customerautomation.service;

import com.example.customerautomation.entity.Employee;
import com.example.customerautomation.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer empId) {
        return employeeRepository.findById(empId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + empId));
    }

    public Employee createEmployee(Employee employee) {
        if (employee.getEmpId() == null) {
            throw new IllegalArgumentException("Employee ID is required");
        }
        if (employeeRepository.existsById(employee.getEmpId())) {
            throw new IllegalArgumentException("Employee already exists with id: " + employee.getEmpId());
        }
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer empId, Employee employee) {
        Employee existing = getEmployeeById(empId);
        existing.setSalary(employee.getSalary());
        existing.setCity(employee.getCity());
        existing.setState(employee.getState());
        return employeeRepository.save(existing);
    }

    public void deleteEmployee(Integer empId) {
        if (!employeeRepository.existsById(empId)) {
            throw new IllegalArgumentException("Employee not found with id: " + empId);
        }
        employeeRepository.deleteById(empId);
    }
}
