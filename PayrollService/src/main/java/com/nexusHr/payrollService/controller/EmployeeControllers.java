package com.nexusHr.payrollService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexusHr.employeeService.entity.Employee;
import com.nexusHr.payrollService.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeControllers {

    @Autowired
    private EmployeeService service;

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        return ResponseEntity.ok(service.getAllEmployees());
    }
}