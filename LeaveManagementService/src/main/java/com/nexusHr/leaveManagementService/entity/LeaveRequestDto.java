package com.nexusHr.leaveManagementService.entity;

import java.time.LocalDate;


public class LeaveRequestDto {

	   private Long leaveRequestId;
	    private Long employeeId;
	    private String employeeName;
	    private String email;
	    private String reason;
	    private LocalDate startDate;
	    private LocalDate endDate;
	    private String status;
	    
	    
		public LeaveRequestDto(Long leaveRequestId, Long employeeId, String employeeName, String email, String reason,
				LocalDate startDate, LocalDate endDate, String status) {
			super();
			this.leaveRequestId = leaveRequestId;
			this.employeeId = employeeId;
			this.employeeName = employeeName;
			this.email = email;
			this.reason = reason;
			this.startDate = startDate;
			this.endDate = endDate;
			this.status = status;
		}
		public Long getLeaveRequestId() {
			return leaveRequestId;
		}
		public void setLeaveRequestId(Long leaveRequestId) {
			this.leaveRequestId = leaveRequestId;
		}
		public Long getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(Long employeeId) {
			this.employeeId = employeeId;
		}
		public String getEmployeeName() {
			return employeeName;
		}
		public void setEmployeeName(String employeeName) {
			this.employeeName = employeeName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
		public LocalDate getStartDate() {
			return startDate;
		}
		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}
		public LocalDate getEndDate() {
			return endDate;
		}
		public void setEndDate(LocalDate endDate) {
			this.endDate = endDate;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}

	

	
}