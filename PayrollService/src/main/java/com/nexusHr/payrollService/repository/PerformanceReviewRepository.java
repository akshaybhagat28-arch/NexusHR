package com.nexusHr.payrollService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nexusHr.payrollService.entity.PerformanceReview;

public interface PerformanceReviewRepository extends JpaRepository<PerformanceReview, Long> {

	List<PerformanceReview> findByEmployeeId(Long employeeId);

	@Query(value = """

						            SELECT
			    pr.id,
			    pr.employee_id,
			    pr.reviewer_id,

			    e.first_name || ' ' || e.last_name AS employee_name,

			    r.first_name || ' ' || r.last_name AS reviewer_name,

			    pr.feedback,

			    pr.rating,

			    pr.review_date

			FROM performance_reviews pr

			JOIN employees e
			ON pr.employee_id = e.id

			JOIN employees r
			ON pr.reviewer_id = r.id

						            """, nativeQuery = true)

	List<Object[]> findAllPerformance();
}
