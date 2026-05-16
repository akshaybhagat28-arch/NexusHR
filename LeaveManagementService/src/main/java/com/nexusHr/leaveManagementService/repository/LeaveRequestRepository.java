package com.nexusHr.leaveManagementService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexusHr.leaveManagementService.entity.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

	List<LeaveRequest> findByEmployeeIdAndPaidFalse(Long employeeId);

}
