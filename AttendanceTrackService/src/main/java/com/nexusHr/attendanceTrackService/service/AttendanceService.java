package com.nexusHr.attendanceTrackService.service;

import java.util.List;
import java.util.Optional;

import com.nexusHr.attendanceTrackService.entity.Attendance;

public interface AttendanceService {

	public Attendance checkIn(Long empId);

	public Optional<Attendance> getAttendanceByEmpId(Long empId);

	public Attendance checkOut(Long empId);

	public List<Attendance> getAttendanceAll();

}
