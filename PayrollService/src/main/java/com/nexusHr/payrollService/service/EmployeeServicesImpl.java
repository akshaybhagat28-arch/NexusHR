package com.nexusHr.payrollService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexusHr.employeeService.entity.Employee;
import com.nexusHr.employeeService.repository.EmployeeRepository;

@Service
public class EmployeeServicesImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

}
