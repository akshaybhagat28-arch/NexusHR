package com.nexusHr.employeeService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexusHr.employeeService.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}