package com.nexusHr.employeeService.service;

import java.util.List;

import com.nexusHr.employeeService.entity.Employee;

public interface EmployeeService {

	public Employee saveEmployee(Employee employee);

	public List<Employee> getAllEmployee();

	public Employee updateEmployee(Employee emp, Long id);

	public String deleteEmployee(Long id);

	public Employee getEmployeeById(Long employeeId);

}
