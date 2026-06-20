package com.nexusHr.employeeService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Double basicSalary;

    private Integer totalWorkingDays;

    // DEPARTMENT MAPPING
    @ManyToOne
    @JoinColumn(name = "department_id")

    @JsonIgnore
    private Department department;

    public Employee() {
    }

    public Employee(Long id,
                    String firstName,
                    String lastName,
                    String email,
                    Double basicSalary,
                    Integer totalWorkingDays,
                    Department department) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.basicSalary = basicSalary;
        this.totalWorkingDays = totalWorkingDays;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Integer getTotalWorkingDays() {
        return totalWorkingDays;
    }

    public void setTotalWorkingDays(Integer totalWorkingDays) {
        this.totalWorkingDays = totalWorkingDays;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}