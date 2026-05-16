package com.nexusHr.attendanceTrackService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.nexusHr.authService", "com.nexusHr.employeeService" })
public class AttendanceTrackingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceTrackingServiceApplication.class, args);
	}
}
