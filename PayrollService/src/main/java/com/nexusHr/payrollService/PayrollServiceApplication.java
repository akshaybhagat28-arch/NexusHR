package com.nexusHr.payrollService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {
	    "com.nexusHr.authService",
	    "com.nexusHr.employeeService"
	})
public class PayrollServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayrollServiceApplication.class, args);
		System.out.println("PayrollServiceApplication");
	}
}
