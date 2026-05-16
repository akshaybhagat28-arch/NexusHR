package com.nexusHr.payrollService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexusHr.payrollService.entity.PerformanceReview;

public interface PerformanceReviewRepository extends JpaRepository<PerformanceReview, Long>{

	List<PerformanceReview> findByEmployeeId(Long employeeId);

}
