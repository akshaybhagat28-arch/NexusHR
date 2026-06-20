package com.nexusHr.attendanceTrackService.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexusHr.attendanceTrackService.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

	Attendance findByEmpIdAndAttendanceDate(Long empId, LocalDate attendanceDate);

	Optional<Attendance> findByEmpId(Long empId);
}
