package com.nexusHr.payrollService.service;

import com.nexusHr.payrollService.entity.PerformanceReview;
import com.nexusHr.payrollService.entity.PerformanceReviewDTO;
import com.nexusHr.payrollService.repository.PerformanceReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.nexusHr.employeeService.entity.Employee;
import com.nexusHr.employeeService.repository.EmployeeRepository;

@Service
public class PerformanceReviewServiceImpl implements PerformanceReviewService {

	@Autowired
	private PerformanceReviewRepository performanceReviewRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	// Add Review
	public PerformanceReview addReview(PerformanceReviewDTO dto) {

		Employee employee = employeeRepository.findById(dto.getEmployeeId())
				.orElseThrow(() -> new RuntimeException("Employee not found"));

		Employee reviewer = employeeRepository.findById(dto.getReviewerId())
				.orElseThrow(() -> new RuntimeException("Reviewer not found"));

		PerformanceReview review = new PerformanceReview();

		review.setEmployee(employee);
		review.setReviewer(reviewer);
		review.setRating(dto.getRating());
		review.setFeedback(dto.getFeedback());
		review.setReviewDate(dto.getReviewDate());

		return performanceReviewRepository.save(review);
	}

	// Get Average Rating
	public Double getAverageRating(Long employeeId) {

		List<PerformanceReview> reviews = performanceReviewRepository.findByEmployeeId(employeeId);

		if (reviews.isEmpty()) {
			return 0.0;
		}

		double total = 0;

		for (PerformanceReview review : reviews) {
			total += review.getRating();
		}

		return total / reviews.size();
	}

	// Performance Level
	public String getPerformanceLevel(Double rating) {

		if (rating >= 4.5) {
			return "Excellent";
		} else if (rating >= 4.0) {
			return "Very Good";
		} else if (rating >= 3.0) {
			return "Good";
		} else if (rating >= 2.0) {
			return "Average";
		}

		return "Poor";
	}

	// DELETE review
	public String deleteReview(Long id) {
		performanceReviewRepository.deleteById(id);
		return "successfuly deleted";
	}

	@Override
	public List<PerformanceReviewDTO> getALLReview() {

		List<Object[]> rows = performanceReviewRepository.findAllPerformance();

		List<PerformanceReviewDTO> list = new ArrayList<>();

		for (Object[] row : rows) {

			PerformanceReviewDTO dto =
				    new PerformanceReviewDTO(

				        ((Number) row[0]).longValue(), // ID

				        ((Number) row[1]).longValue(), // employeeId

				        ((Number) row[2]).longValue(), // reviewerId

				        row[3].toString(), // employeeName

				        row[4].toString(), // reviewerName

				        row[5].toString(), // feedback

				        Integer.parseInt(
				            row[6].toString()
				        ), // rating

				        ((java.sql.Date) row[7])
				            .toLocalDate() // reviewDate
				);

			list.add(dto);
		}

		return list;
	}
}