package com.nexusHr.payrollService.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.nexusHr.employeeService.entity.Employee;

@Entity
@Table(name = "attendance")
public class AttendanceEmployee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate attendanceDate;

	private String status;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	

	public AttendanceEmployee() {
		super();
	}

	public AttendanceEmployee(Long id, LocalDate attendanceDate, String status, Employee employee) {
		super();
		this.id = id;
		this.attendanceDate = attendanceDate;
		this.status = status;
		this.employee = employee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(LocalDate attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	
}
