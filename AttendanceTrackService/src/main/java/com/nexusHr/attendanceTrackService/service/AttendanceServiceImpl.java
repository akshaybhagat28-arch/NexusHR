package com.nexusHr.attendanceTrackService.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.nexusHr.attendanceTrackService.entity.Attendance;
import com.nexusHr.attendanceTrackService.repository.AttendanceRepository;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public Attendance checkIn(Long empId) {

		LocalDate today = LocalDate.now();

		// 🔥 Atomic lock (prevents duplicate)
		Boolean isNew = redisTemplate.opsForValue().setIfAbsent(String.valueOf(empId), "CHECKED_IN", Duration.ofHours(12));

		if (Boolean.FALSE.equals(isNew)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already checked in today");
		}

		Attendance attendance = new Attendance();
		attendance.setEmpId(empId);
		attendance.setAttendanceDate(today);
		attendance.setCheckInTime(LocalDateTime.now());
		attendance.setStatus("PRESENT");

		Attendance saved = attendanceRepository.save(attendance);

		return saved;
	}

	// CHECK-OUT
	public Attendance checkOut(Long empId) {

		LocalDate today = LocalDate.now();

		Attendance attendance = (Attendance) redisTemplate.opsForValue().get("CHECKIN_" + empId);

		if (attendance == null) {
			attendance = attendanceRepository.findByEmpIdAndAttendanceDate(empId, today);
		}

		attendance.setCheckOutTime(LocalDateTime.now());

		// calculate hours
		if (attendance.getCheckInTime() != null) {
			Duration duration = Duration.between(attendance.getCheckInTime(), attendance.getCheckOutTime());

			System.out.println("TOTAL HOURS => " + duration.toHoursPart());
		}

		Attendance updated = attendanceRepository.save(attendance);

		return updated;
	}

	// GET ATTENDANCE BY ID
	public Optional<Attendance> getAttendanceByEmpId(Long empId) {

	    return attendanceRepository.findByEmpId(empId);
	}


	

	public List<Attendance> getAttendanceAll() {
		return attendanceRepository.findAll();
	}

	
}