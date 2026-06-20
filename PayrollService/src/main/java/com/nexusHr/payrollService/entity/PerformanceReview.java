package com.nexusHr.payrollService.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.nexusHr.employeeService.entity.Employee;

@Entity
@Table(name = "performance_reviews")
public class PerformanceReview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Employee who is being reviewed
	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false)
	private Employee employee;

	// Employee who is giving the review
	@ManyToOne
	@JoinColumn(name = "reviewer_id", nullable = false)
	private Employee reviewer;

	private Integer rating;

	@Column(columnDefinition = "TEXT")
	private String feedback;

	private LocalDate reviewDate;

	// Constructors
	public PerformanceReview() {
	}

	

	public PerformanceReview(Long id, Employee employee, Employee reviewer, Integer rating, String feedback,
			LocalDate reviewDate) {
		super();
		this.id = id;
		this.employee = employee;
		this.reviewer = reviewer;
		this.rating = rating;
		this.feedback = feedback;
		this.reviewDate = reviewDate;
	}



	// Getters and Setters
	public Long getId() {
		return id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Employee getReviewer() {
		return reviewer;
	}

	public void setReviewer(Employee reviewer) {
		this.reviewer = reviewer;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public LocalDate getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(LocalDate reviewDate) {
		this.reviewDate = reviewDate;
	}
}