package com.nexusHr.payrollService.service;

import java.util.List;
import java.util.Optional;

import com.nexusHr.payrollService.entity.PerformanceReview;

public interface PerformanceReviewService {

	public PerformanceReview saveReview(PerformanceReview review);

	public List<PerformanceReview> getAllReviews();

	public Optional<PerformanceReview> getReviewById(Long id);

	public List<PerformanceReview> getReviewsByEmployeeId(Long employeeId);

	public void deleteReview(Long id);

}
