package com.nexusHr.attendanceTrackService.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.nexusHr.attendanceTrackService.entity.Attendance;
import com.nexusHr.attendanceTrackService.repository.AttendanceRepository;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	// CHECK-IN
	public Attendance checkIn(String email) {

		LocalDate today = LocalDate.now();
		String key = buildKey(email, today);

		System.out.println("CHECKIN KEY => " + key);

		// 🔥 Atomic lock (prevents duplicate)
		Boolean isNew = redisTemplate.opsForValue().setIfAbsent(key, "LOCK", Duration.ofHours(24));

		if (Boolean.FALSE.equals(isNew)) {
			throw new RuntimeException("Already checked in today");
		}

		Attendance attendance = new Attendance();
		attendance.setEmployeeEmail(email);
		attendance.setAttendanceDate(today);
		attendance.setCheckInTime(LocalDateTime.now());
		attendance.setStatus("PRESENT");

		Attendance saved = attendanceRepository.save(attendance);

		// Save in Redis
		redisTemplate.opsForValue().set(key, saved, Duration.ofHours(24));

		System.out.println("SAVED IN REDIS => " + key);

		return saved;
	}

	// CHECK-OUT
	public Attendance checkOut(String email) {

		LocalDate today = LocalDate.now();
		String key = buildKey(email, today);

		Attendance attendance = (Attendance) redisTemplate.opsForValue().get(key);

		if (attendance == null) {
			attendance = attendanceRepository.findByEmployeeEmailAndAttendanceDate(email, today);
		}

		attendance.setCheckOutTime(LocalDateTime.now());

		// calculate hours
		if (attendance.getCheckInTime() != null) {
			Duration duration = Duration.between(attendance.getCheckInTime(), attendance.getCheckOutTime());

			System.out.println("TOTAL HOURS => " + duration.toHoursPart());
		}

		Attendance updated = attendanceRepository.save(attendance);

		redisTemplate.opsForValue().set(key, updated, Duration.ofHours(24));

		return updated;
	}

	// GET ATTENDANCE
	public Attendance getAttendance(String email) {

		LocalDate today = LocalDate.now();
		String key = buildKey(email, today);

		Object cached = redisTemplate.opsForValue().get(key);

		if (cached != null) {
			return (Attendance) cached;
		}

		return attendanceRepository.findByEmployeeEmailAndAttendanceDate(email, today);
	}

	// KEY BUILDER
	private String buildKey(String email, LocalDate date) {
		return "attendance:" + email + ":" + date;
	}
}