//package com.nexusHr.payrollService.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.nexusHr.attendanceTrackService.entity.Attendance;
//import com.nexusHr.attendanceTrackService.service.AttendanceService;
//
//@RestController
//@RequestMapping("/api/attendance")
//@CrossOrigin(origins = "http://localhost:5173")
//public class AttendancesController {
//
//	@Autowired
//	private AttendanceService attendanceService;
//
//	@GetMapping
//	public List<Attendance> getAll() {
//		return attendanceService.getAttendanceAll();
//	}
//
//	@GetMapping("/checkin")
//	public Attendance checkIn(@RequestParam String email) {
//		return attendanceService.checkIn(email);
//	}
//
//	@PostMapping("/checkout")
//	public Attendance checkOut(@RequestParam String email) {
//		return attendanceService.checkOut(email);
//	}
//
//	@GetMapping("/{email}")
//	public Attendance get(@RequestParam String email) {
//		return attendanceService.getAttendance(email);
//	}
//
//}