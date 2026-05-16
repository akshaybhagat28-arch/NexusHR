package com.nexusHr.leaveManagementService.service;

import java.util.List;

import com.nexusHr.leaveManagementService.entity.LeaveRequest;
import com.nexusHr.leaveManagementService.entity.LeaveResponse;

public interface LeaveService {

	public LeaveRequest applyLeave(LeaveRequest request);

	public LeaveRequest approveLeave(Long id);

	public LeaveRequest rejectLeave(Long id);

	public List<LeaveRequest> getAllLeaves();

}
