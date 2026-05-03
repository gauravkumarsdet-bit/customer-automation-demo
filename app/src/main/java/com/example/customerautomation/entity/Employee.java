package com.example.customerautomation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @Column(name = "emp_id")
    private Integer empId;

    @Column(nullable = false)
    private Integer salary;

    @Column(nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    public Employee() {
    }

    public Employee(Integer empId, Integer salary, String city, String state) {
        this.empId = empId;
        this.salary = salary;
        this.city = city;
        this.state = state;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
