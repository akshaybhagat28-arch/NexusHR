package com.nexusHr.payrollService.service;

import java.util.List;

import com.nexusHr.payrollService.entity.PerformanceReview;
import com.nexusHr.payrollService.entity.PerformanceReviewDTO;

public interface PerformanceReviewService {

	public PerformanceReview addReview(PerformanceReviewDTO dto);

	public Double getAverageRating(Long employeeId);

	public String getPerformanceLevel(Double rating);

	public String deleteReview(Long id);

	public List<PerformanceReviewDTO> getALLReview();

}
