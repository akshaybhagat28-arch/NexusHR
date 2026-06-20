package com.nexusHr.payrollService.entity;

import java.time.LocalDate;

public class PerformanceReviewDTO {

	private Long id;
	private Long employeeId;
	private Long reviewerId;
	private String employeeName;
	private String reviewerName;
	private String feedback;
	private Integer rating;
	private LocalDate reviewDate;

	public PerformanceReviewDTO() {
		super();
	}


	public PerformanceReviewDTO(Long id, Long employeeId, Long reviewerId, String employeeName, String reviewerName,
			String feedback, Integer rating, LocalDate reviewDate) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.reviewerId = reviewerId;
		this.employeeName = employeeName;
		this.reviewerName = reviewerName;
		this.feedback = feedback;
		this.rating = rating;
		this.reviewDate = reviewDate;
	}



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}


	public Long getReviewerId() {
		return reviewerId;
	}


	public void setReviewerId(Long reviewerId) {
		this.reviewerId = reviewerId;
	}


	public String getEmployeeName() {
		return employeeName;
	}


	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	public String getReviewerName() {
		return reviewerName;
	}


	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}


	public String getFeedback() {
		return feedback;
	}


	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}


	public Integer getRating() {
		return rating;
	}


	public void setRating(Integer rating) {
		this.rating = rating;
	}


	public LocalDate getReviewDate() {
		return reviewDate;
	}


	public void setReviewDate(LocalDate reviewDate) {
		this.reviewDate = reviewDate;
	}


	@Override
	public String toString() {
		return "PerformanceReviewDTO [id=" + id + ", employeeId=" + employeeId + ", reviewerId=" + reviewerId
				+ ", employeeName=" + employeeName + ", reviewerName=" + reviewerName + ", feedback=" + feedback
				+ ", rating=" + rating + ", reviewDate=" + reviewDate + "]";
	}

}