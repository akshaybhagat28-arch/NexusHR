package com.nexusHr.attendanceTrackService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nexusHr.attendanceTrackService.entity.Attendance;
import com.nexusHr.attendanceTrackService.service.AttendanceService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/checkin")
    public Attendance checkIn(@RequestParam String email) {
        return attendanceService.checkIn(email);
    }

    @PostMapping("/checkout")
    public Attendance checkOut(@RequestParam String email) {
        return attendanceService.checkOut(email);
    }

    @GetMapping
    public Attendance get(@RequestParam String email) {
        return attendanceService.getAttendance(email);
    }
}