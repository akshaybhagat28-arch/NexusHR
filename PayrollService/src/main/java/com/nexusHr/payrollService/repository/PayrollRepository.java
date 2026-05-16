package com.nexusHr.payrollService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nexusHr.payrollService.entity.Payroll;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {
	@Query("""
		    SELECT p FROM Payroll p
		    JOIN FETCH p.employee e
		    WHERE e.id = :id
		""")
		Payroll findByIdWithEmployee(@Param("id") Long id);
}
