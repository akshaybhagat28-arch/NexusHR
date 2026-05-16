package com.nexusHr.payrollService.entity;

import java.time.LocalDate;

public class PayrollRequestDTO {

	private Long id;
	private String employeeName;
	private String month;

	private Double grossSalary;
	private Double leaveDeduction;
	private Double netSalary;

	private LocalDate generatedDate;

	public PayrollRequestDTO() {
		super();
	}

	public PayrollRequestDTO(Long id, String employeeName, String month, Double grossSalary, Double leaveDeduction,
			Double netSalary, LocalDate generatedDate) {
		super();
		this.id = id;
		this.employeeName = employeeName;
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

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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
