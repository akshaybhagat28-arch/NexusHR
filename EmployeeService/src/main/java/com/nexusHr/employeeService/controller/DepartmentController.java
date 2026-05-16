package com.nexusHr.employeeService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexusHr.employeeService.entity.Department;
import com.nexusHr.employeeService.service.DepartmentService;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/saveDept")
	public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
		return ResponseEntity.ok(departmentService.saveDepartment(department));

	}

}
