package com.nexusHr.payrollService.entity;

import java.time.LocalDate;

import com.nexusHr.employeeService.entity.Employee;

import jakarta.persistence.*;

@Entity
@Table(name = "payroll")
public class Payroll {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id", nullable = false)
	private Employee employee;

	private String month;

	private Double grossSalary;
	private Double leaveDeduction;
	private Double netSalary;

	private LocalDate generatedDate;

	@Transient
	public String getEmployeeName() {
		if (employee == null)
			return "";

		String first = employee.getFirstName() != null ? employee.getFirstName() : "";
		String last = employee.getLastName() != null ? employee.getLastName() : "";

		return (first + " " + last).trim();
	}

	// Constructors
	public Payroll() {
	}

	public Payroll(Long id, Employee employee, String month, Double grossSalary, Double leaveDeduction,
			Double netSalary, LocalDate generatedDate) {
		super();
		this.id = id;
		this.employee = employee;
		this.month = month;
		this.grossSalary = grossSalary;
		this.leaveDeduction = leaveDeduction;
		this.netSalary = netSalary;
		this.generatedDate = generatedDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Double getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(Double grossSalary) {
		this.grossSalary = grossSalary;
	}

	public Double getLeaveDeduction() {
		return leaveDeduction;
	}

	public void setLeaveDeduction(Double leaveDeduction) {
		this.leaveDeduction = leaveDeduction;
	}

	public Double getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(Double netSalary) {
		this.netSalary = netSalary;
	}

	public LocalDate getGeneratedDate() {
		return generatedDate;
	}

	public void setGeneratedDate(LocalDate generatedDate) {
		this.generatedDate = generatedDate;
	}

}
