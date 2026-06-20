package com.nexusHr.leaveManagementService.service;

import java.util.List;

import com.nexusHr.leaveManagementService.entity.LeaveRequest;
import com.nexusHr.leaveManagementService.entity.LeaveRequestDto;

public interface LeaveService {

	public LeaveRequest applyLeave(LeaveRequestDto dto);

	public LeaveRequest approveLeave(Long id);

	public LeaveRequest rejectLeave(Long id);

	public List<Object[]> getAllLeaves();

}
