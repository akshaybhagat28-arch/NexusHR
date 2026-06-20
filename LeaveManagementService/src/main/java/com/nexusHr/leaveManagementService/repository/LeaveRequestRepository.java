package com.nexusHr.leaveManagementService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nexusHr.leaveManagementService.entity.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

	List<LeaveRequest> findByEmployeeIdAndPaidFalse(Long employeeId);

	
	@Query("""
			SELECT l FROM LeaveRequest l
			WHERE l.employee.id = :employeeId
			AND l.status = 'APPROVED'
			AND MONTH(l.startDate) = :month
			""")
			List<LeaveRequest> getApprovedLeavesByMonth(
			        @Param("employeeId") Long employeeId,
			        @Param("month") int month);
	@Query(value = """
		    SELECT 
		        lr.id AS leaveRequestId,
		        e.id AS employeeId,
		        CONCAT(e.first_name, ' ', e.last_name) AS employeeName,
		        e.email AS email,
		        lr.reason AS reason,
		        lr.start_date AS startDate,
		        lr.end_date AS endDate,
		        lr.status AS status
		    FROM leave_requests lr
		    JOIN employees e
		    ON lr.employee_id = e.id
		    """, nativeQuery = true)
		List<Object[]> getLeaveRequestsWithEmployeeName();

}
