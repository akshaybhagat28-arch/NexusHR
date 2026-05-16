package com.nexusHr.attendanceTrackService.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexusHr.attendanceTrackService.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

	Attendance findByEmployeeEmailAndAttendanceDate(String email, LocalDate now);
	
}
