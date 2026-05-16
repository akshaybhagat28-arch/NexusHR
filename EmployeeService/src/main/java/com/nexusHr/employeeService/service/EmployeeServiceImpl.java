package com.nexusHr.employeeService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexusHr.employeeService.entity.Department;
import com.nexusHr.employeeService.entity.Employee;
import com.nexusHr.employeeService.repository.DepartmentRepository;
import com.nexusHr.employeeService.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee saveEmployee(Employee employee) {

		Long deptId = employee.getDepartment().getId();

		Department department = departmentRepository.findById(deptId)
				.orElseThrow(() -> new RuntimeException("Department not found"));

		employee.setDepartment(department);

		return employeeRepository.save(employee);
	}

	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	@SuppressWarnings("deprecation")
	public Employee updateEmployee(Employee emp, Long id) {
		Employee employee = employeeRepository.getById(id);
		if (employee != null) {
			employee.setId(id);
			employee.setFirstName(emp.getFirstName());
			employee.setLastName(emp.getLastName());
			employee.setDepartment(emp.getDepartment());
			employeeRepository.save(employee);
		}
		return emp;
	}

	public String deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
		return "Delete Succesfully";
	}

	@SuppressWarnings("deprecation")
	@Override
	public Employee getEmployeeById(Long employeeId) {
		// TODO Auto-generated method stub
		return employeeRepository.getById(employeeId);
	}
}
