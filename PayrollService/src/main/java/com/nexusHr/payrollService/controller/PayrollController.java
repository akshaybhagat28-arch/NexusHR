package com.nexusHr.payrollService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nexusHr.authService.entity.AuthResponse;
import com.nexusHr.authService.entity.LoginRequest;
import com.nexusHr.authService.entity.SignupRequest;
import com.nexusHr.authService.service.AuthService;
import com.nexusHr.employeeService.entity.Employee;
import com.nexusHr.employeeService.service.EmployeeService;
import com.nexusHr.payrollService.entity.Payroll;
import com.nexusHr.payrollService.entity.PayrollRequestDTO;

import java.time.LocalDate;
import java.util.List;

import com.nexusHr.payrollService.service.PayrollService;

@RestController
@RequestMapping("/api/payroll")
@CrossOrigin(origins = {
	    "http://localhost:5173",
	    "https://nexus-hr-project-7wwyy714-akshaybhagat28-1992s-projects.vercel.app"
	})
public class PayrollController {

	@Autowired
	private PayrollService payrollService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	AuthService authService;

	@PostMapping("/signup")
	public String signup(@RequestBody SignupRequest req) {
		return authService.signup(req);
	}

	@PostMapping("/login")
	public AuthResponse login(@RequestBody LoginRequest req) {
		return authService.login(req);
	}

	@PostMapping("/generate")
	public ResponseEntity<Payroll> generate(@RequestBody PayrollRequestDTO dto) {
		return ResponseEntity.ok(payrollService.generatePayroll(dto.getId(), dto.getMonth()));
	}

	@GetMapping("/getALL")
	public List<Payroll> getAll() {
		return payrollService.getAllPayrolls();
	}

	@GetMapping("/{id}")
	public Payroll getById(@PathVariable Long id) {
		return payrollService.getPayrollById(id);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		payrollService.deletePayroll(id);
	}

	@GetMapping("/payslip/{selectedEmployeeId}/{month}")
	public ResponseEntity<byte[]> downloadPayslip(@PathVariable Long selectedEmployeeId, @PathVariable String month) {

		try {

			byte[] pdf = payrollService.generatePayslip(selectedEmployeeId, month);

			Employee employee = employeeService.getEmployeeById(selectedEmployeeId);

			String fileName = employee.getFirstName().toLowerCase() + "_" + employee.getLastName().toLowerCase() + "_"
					+ month.toLowerCase() + "_" + LocalDate.now().getYear() + "_payslip.pdf";

			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
					.contentType(MediaType.APPLICATION_PDF).body(pdf);

		} catch (Exception e) {

			return ResponseEntity.internalServerError().build();
		}
	}

}