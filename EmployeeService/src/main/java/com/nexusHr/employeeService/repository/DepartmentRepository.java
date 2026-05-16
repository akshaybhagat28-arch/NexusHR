package com.nexusHr.employeeService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexusHr.employeeService.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}