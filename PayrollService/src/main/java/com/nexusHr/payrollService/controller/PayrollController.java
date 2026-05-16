package com.nexusHr.payrollService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nexusHr.employeeService.entity.Employee;
import com.nexusHr.employeeService.service.EmployeeService;
import com.nexusHr.payrollService.entity.Payroll;
import com.nexusHr.payrollService.entity.PayrollRequestDTO;

import java.time.LocalDate;
import java.util.List;

import com.nexusHr.payrollService.service.PayrollService;

@RestController
@RequestMapping("/api/payroll")
public class PayrollController {

	@Autowired
	private PayrollService payrollService;
	
	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/generate")
	public ResponseEntity<Payroll> generate(@RequestBody PayrollRequestDTO dto) {
		return ResponseEntity.ok(payrollService.generatePayroll(dto.getId(), dto.getMonth()));
	}

	@GetMapping
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

	@GetMapping("/payslip/{employeeId}")
	public ResponseEntity<byte[]> downloadPayslip(
	        @PathVariable Long employeeId,
	        @RequestParam String month) {

	    try {

	        byte[] pdf =
	                payrollService.generatePayslip(employeeId, month);

	        Employee employee =
	                employeeService.getEmployeeById(employeeId);

	        String fileName =
	                employee.getFirstName().toLowerCase()
	                + "_"
	                + employee.getLastName().toLowerCase()
	                + "_"
	                + month.toLowerCase()
	                + "_"
	                + LocalDate.now().getYear()
	                + "_payslip.pdf";

	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION,
	                        "attachment; filename=\"" + fileName + "\"")
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(pdf);

	    } catch (Exception e) {

	        return ResponseEntity.internalServerError().build();
	    }
	}
}