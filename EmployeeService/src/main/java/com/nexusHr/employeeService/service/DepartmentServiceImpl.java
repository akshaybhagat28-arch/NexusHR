package com.nexusHr.employeeService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexusHr.employeeService.entity.Department;
import com.nexusHr.employeeService.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Department saveDepartment(Department dept) {
		return departmentRepository.save(dept);
	}

}
