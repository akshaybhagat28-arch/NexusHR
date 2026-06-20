//package com.nexusHr.leaveManagementService.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import com.nexusHr.authService.entity.AuthResponse;
//import com.nexusHr.authService.entity.LoginRequest;
//import com.nexusHr.authService.entity.SignupRequest;
//import com.nexusHr.authService.service.AuthService;
//import com.nexusHr.leaveManagementService.entity.LeaveRequest;
//import com.nexusHr.leaveManagementService.entity.LeaveRequestDto;
//import com.nexusHr.leaveManagementService.service.LeaveService;
//
//@RestController
//@RequestMapping("/api/leaves")
//public class LeaveController {
//
//	@Autowired
//	private LeaveService leaveService;
//
//	@Autowired
//	AuthService authService;
//
//	@PostMapping("/signup")
//	public String signup(@RequestBody SignupRequest req) {
//		return authService.signup(req);
//	}
//
//	@PostMapping("/login")
//	public AuthResponse login(@RequestBody LoginRequest req) {
//		return authService.login(req);
//	}
//
//	// EMPLOYEE can apply leave
//	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
//	@PostMapping("/apply")
//	public ResponseEntity<LeaveRequest> applyLeave(@RequestBody LeaveRequestDto request) {
//		return ResponseEntity.ok(leaveService.applyLeave(request));
//	}
//
//	// MANAGER can approve
//	@PreAuthorize("hasRole('ROLE_MANAGER')")
//	@PutMapping("/{id}/approve")
//	public ResponseEntity<LeaveRequest> approveLeave(@PathVariable Long id) {
//
//		LeaveRequest response = leaveService.approveLeave(id);
//
//		return ResponseEntity.ok(response);
//	}
//
//	// MANAGER can reject
//	@PreAuthorize("hasAuthority('ROLE_MANAGER')")
//	@PutMapping("/{id}/reject")
//	public ResponseEntity<LeaveRequest> rejectLeave(@PathVariable Long id) {
//
//		LeaveRequest response = leaveService.rejectLeave(id);
//
//		return ResponseEntity.ok(response);
//	}
//
//	// HR or MANAGER can view all leaves
//	@PreAuthorize("hasAnyRole('ROLE_HR','ROLE_MANAGER')")
//	@GetMapping
//	public ResponseEntity<List<Object[]>> getAllLeaves() {
//
//		List<Object[]> leaves = leaveService.getAllLeaves();
//
//		return ResponseEntity.ok(leaves);
//	}
//}