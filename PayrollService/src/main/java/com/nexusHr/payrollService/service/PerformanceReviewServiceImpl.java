package com.nexusHr.payrollService.service;

import com.nexusHr.payrollService.entity.PerformanceReview;
import com.nexusHr.payrollService.repository.PerformanceReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerformanceReviewServiceImpl implements PerformanceReviewService {

	@Autowired
	private PerformanceReviewRepository repository;

	// CREATE / SAVE review
	public PerformanceReview saveReview(PerformanceReview review) {
		return repository.save(review);
	}

	// GET all reviews
	public List<PerformanceReview> getAllReviews() {
		return repository.findAll();
	}

	// GET review by ID
	public Optional<PerformanceReview> getReviewById(Long id) {
		return repository.findById(id);
	}

	// GET reviews by employee
	public List<PerformanceReview> getReviewsByEmployeeId(Long employeeId) {
		return repository.findByEmployeeId(employeeId);
	}

	// DELETE review
	public void deleteReview(Long id) {
		repository.deleteById(id);
	}
}