package com.nexusHr.payrollService.controller;

import com.nexusHr.payrollService.entity.PerformanceReview;
import com.nexusHr.payrollService.service.PerformanceReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
public class PerformanceReviewController {

	@Autowired
	private PerformanceReviewService service;

	// CREATE review
	@PostMapping
	public PerformanceReview createReview(@RequestBody PerformanceReview review) {
		return service.saveReview(review);
	}

	// GET all reviews
	@GetMapping
	public List<PerformanceReview> getAllReviews() {
		return service.getAllReviews();
	}

	// GET review by ID
	@GetMapping("/{id}")
	public Optional<PerformanceReview> getReviewById(@PathVariable Long id) {
		return service.getReviewById(id);
	}

	// GET reviews by employee ID
	@GetMapping("/employee/{employeeId}")
	public List<PerformanceReview> getByEmployee(@PathVariable Long employeeId) {
		return service.getReviewsByEmployeeId(employeeId);
	}

	// DELETE review
	@DeleteMapping("/{id}")
	public String deleteReview(@PathVariable Long id) {
		service.deleteReview(id);
		return "Review deleted successfully";
	}
}
