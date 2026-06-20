package com.nexusHr.payrollService.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nexusHr.attendanceTrackService.entity.Attendance;
import com.nexusHr.attendanceTrackService.service.AttendanceService;
import com.nexusHr.authService.entity.AuthResponse;
import com.nexusHr.authService.entity.LoginRequest;
import com.nexusHr.authService.entity.SignupRequest;
import com.nexusHr.authService.service.AuthService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	@Autowired
	AuthService authService;

	@PostMapping("/signup")
	public String signup(@RequestBody SignupRequest req) {
		return authService.signup(req);
	}

	@PostMapping("/login")
	public AuthResponse login(@RequestBody LoginRequest req) {
		return authService.login(req);
	}

	@PostMapping("/checkin")
	public Attendance checkIn(@RequestParam Long empId) {
		return attendanceService.checkIn(empId);
	}

	@PostMapping("/checkout")
	public Attendance checkOut(@RequestParam Long empId) {
		return attendanceService.checkOut(empId);
	}

	@GetMapping("/get/{empId}")
	public Optional<Attendance> get(@PathVariable Long empId) {

		return attendanceService.getAttendanceByEmpId(empId);
	}

	@GetMapping
	public List<Attendance> getAll() {
		return attendanceService.getAttendanceAll();
	}
}