package com.nexusHr.attendanceTrackService.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.nexusHr.employeeService.entity.Employee;

import jakarta.persistence.*;

@Entity
@Table(name = "attendance")
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long empId;

	private LocalDate attendanceDate;

	private LocalDateTime checkInTime;

	private LocalDateTime checkOutTime;

	private String employeeEmail;

	private String status;

	public Attendance() {
	}

	public Attendance(Long id, Long empId, LocalDate attendanceDate, LocalDateTime checkInTime,
			LocalDateTime checkOutTime, String employeeEmail, String status) {
		super();
		this.id = id;
		this.empId = empId;
		this.attendanceDate = attendanceDate;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.employeeEmail = employeeEmail;
		this.status = status;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public LocalDate getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(LocalDate attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public LocalDateTime getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(LocalDateTime checkInTime) {
		this.checkInTime = checkInTime;
	}

	public LocalDateTime getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(LocalDateTime checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}