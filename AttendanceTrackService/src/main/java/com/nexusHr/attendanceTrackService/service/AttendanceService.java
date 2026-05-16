package com.nexusHr.attendanceTrackService.service;

import com.nexusHr.attendanceTrackService.entity.Attendance;

public interface AttendanceService {

	public Attendance checkIn(String email);

	public Attendance getAttendance(String email);

	public Attendance checkOut(String email);

}
