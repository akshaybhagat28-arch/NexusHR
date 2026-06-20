package com.nexusHr.employeeService.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String departmentName;

    @OneToMany(mappedBy = "department",
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Employee> employees;

    public Department() {
    }

    public Department(Long id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    public Long getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public void setId(Long id) {
		this.id = id;
	}
}