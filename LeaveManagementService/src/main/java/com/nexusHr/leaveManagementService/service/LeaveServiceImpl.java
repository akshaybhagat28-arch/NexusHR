package com.nexusHr.leaveManagementService.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nexusHr.authService.entity.User;
import com.nexusHr.authService.repository.UserRepository;
import com.nexusHr.employeeService.entity.Employee;
import com.nexusHr.employeeService.repository.EmployeeRepository;
import com.nexusHr.leaveManagementService.entity.LeaveRequest;
import com.nexusHr.leaveManagementService.entity.LeaveResponse;
import com.nexusHr.leaveManagementService.entity.LeaveStatus;
import com.nexusHr.leaveManagementService.repository.LeaveRequestRepository;

@Service
public class LeaveServiceImpl implements LeaveService {

	@Autowired
	private LeaveRequestRepository leaveRequestRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public LeaveRequest applyLeave(LeaveRequest leaveRequest) {

	    Employee employee = employeeRepository.findById(
	            leaveRequest.getEmployee().getId()
	    ).orElseThrow(() -> new RuntimeException("Employee not found"));

	    LocalDate startDate = leaveRequest.getStartDate();
	    LocalDate endDate = leaveRequest.getEndDate();

	    if (startDate == null || endDate == null) {
	        throw new RuntimeException("Start date and end date required");
	    }

	    if (endDate.isBefore(startDate)) {
	        throw new RuntimeException("End date cannot be before start date");
	    }

	    // auto calculate total leave days
	    int totalDays = (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;

	    leaveRequest.setEmployee(employee);
	    leaveRequest.setTotalDays(totalDays);
	    leaveRequest.setPaid(false);
	    leaveRequest.setStatus(LeaveStatus.PENDING);

	    return leaveRequestRepository.save(leaveRequest);
	}

	public LeaveRequest approveLeave(Long id) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String username = authentication.getName();

		User user = userRepository.findByUsername(username);

		LeaveRequest leave = leaveRequestRepository.findById(id).orElseThrow();

		leave.setStatus(LeaveStatus.APPROVED);

		// Employee object set
		leave.setApprovedBy(user.getUsername());

		return leaveRequestRepository.save(leave);
	}

	public LeaveRequest rejectLeave(Long id) {

		LeaveRequest leave = leaveRequestRepository.findById(id).orElseThrow();

		leave.setStatus(LeaveStatus.REJECTED);

		return leaveRequestRepository.save(leave);
	}

	@Override
	public List<LeaveRequest> getAllLeaves() {
		return leaveRequestRepository.findAll();
	}

}