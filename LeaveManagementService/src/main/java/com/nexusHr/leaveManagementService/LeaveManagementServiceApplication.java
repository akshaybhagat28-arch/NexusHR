package com.nexusHr.leaveManagementService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
		
@SpringBootApplication
@ComponentScan(basePackages = { "com.nexusHr.authService", "com.nexusHr.employeeService" })
public class LeaveManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaveManagementServiceApplication.class, args);
	}
}
