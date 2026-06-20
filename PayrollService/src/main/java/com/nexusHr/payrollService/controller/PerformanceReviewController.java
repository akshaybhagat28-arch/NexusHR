package com.nexusHr.payrollService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nexusHr.payrollService.entity.PerformanceReview;
import com.nexusHr.payrollService.entity.PerformanceReviewDTO;
import com.nexusHr.payrollService.service.PerformanceReviewService;


@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:5173")
public class PerformanceReviewController {

	@Autowired
	private PerformanceReviewService reviewService;

	// Add Review
	@PostMapping
	public PerformanceReview addReview(@RequestBody PerformanceReviewDTO dto) {
		
		return reviewService.addReview(dto);
	}
	
	@GetMapping
	public List<PerformanceReviewDTO> getALLReview(){
		System.out.println(reviewService.getALLReview().toString());
		return reviewService.getALLReview();
	}

	// Get Average Rating
	@GetMapping("/average/{employeeId}")
	public Double getAverageRating(@PathVariable Long employeeId) {

		return reviewService.getAverageRating(employeeId);
	}

	// Get Performance Level
	@GetMapping("/performance/{employeeId}")
	public String getPerformance(@PathVariable Long employeeId) {

		Double rating = reviewService.getAverageRating(employeeId);

		return reviewService.getPerformanceLevel(rating);
	}
	
	@DeleteMapping("delete/{id}")
	public String deleteReivew(@PathVariable Long id) {
		return reviewService.deleteReview(id);
	}
	
	
}