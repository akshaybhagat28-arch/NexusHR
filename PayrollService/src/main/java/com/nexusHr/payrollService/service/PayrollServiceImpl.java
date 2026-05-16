package com.nexusHr.payrollService.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.nexusHr.employeeService.entity.Employee;
import com.nexusHr.employeeService.repository.EmployeeRepository;
import com.nexusHr.leaveManagementService.entity.LeaveRequest;
import com.nexusHr.leaveManagementService.repository.LeaveRequestRepository;
import com.nexusHr.payrollService.entity.Payroll;
import com.nexusHr.payrollService.repository.PayrollRepository;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayrollServiceImpl implements PayrollService {

	@Autowired
	private PayrollRepository payrollRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private LeaveRequestRepository leaveRepository;

	@Override
	public Payroll generatePayroll(Long employeeId, String month) {

		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new RuntimeException("Employee not found"));

		List<LeaveRequest> unpaidLeaves = leaveRepository.findByEmployeeIdAndPaidFalse(employeeId);

		int totalUnpaidDays = unpaidLeaves.stream().mapToInt(l -> l.getTotalDays() != null ? l.getTotalDays() : 0)
				.sum();

		double basicSalary = Optional.ofNullable(employee.getBasicSalary()).orElse(0.0);

		Integer workingDays = employee.getTotalWorkingDays();

		if (workingDays == null || workingDays <= 0) {
			throw new RuntimeException("Invalid total working days");
		}

		// unpaid leave working days se jyada nahi hone chahiye
		if (totalUnpaidDays > workingDays) {
			totalUnpaidDays = workingDays;
		}

		// per day salary
		double perDaySalary = basicSalary / workingDays;

		// leave deduction
		double deduction = perDaySalary * totalUnpaidDays;

		// rounding
		deduction = Math.round(deduction * 100.0) / 100.0;

		// net salary
		double netSalary = basicSalary - deduction;

		// rounding
		netSalary = Math.round(netSalary * 100.0) / 100.0;

		// negative salary avoid
		if (netSalary < 0) {
			netSalary = 0;
		}

		Payroll payroll = new Payroll();
		payroll.setEmployee(employee);
		payroll.setMonth(month);
		payroll.setGrossSalary(basicSalary);
		payroll.setLeaveDeduction(deduction);
		payroll.setNetSalary(netSalary);
		payroll.setGeneratedDate(LocalDate.now());

		return payrollRepository.save(payroll);
	}

	@Override
	public Payroll updatePayroll(Long id, Payroll payroll) {
		Payroll existing = payrollRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));

		existing.setMonth(payroll.getMonth());
		existing.setGrossSalary(payroll.getGrossSalary());
		existing.setLeaveDeduction(payroll.getLeaveDeduction());

		double net = existing.getGrossSalary() - existing.getLeaveDeduction();
		existing.setNetSalary(net);

		return payrollRepository.save(existing);
	}

	@Override
	public void deletePayroll(Long id) {
		payrollRepository.deleteById(id);
	}

	@Override
	public List<Payroll> getAllPayrolls() {
		return payrollRepository.findAll();
	}

	@Override
	public Payroll getPayrollById(Long id) {
		return payrollRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
	}

	// ✅ PDF GENERATION SERVICE METHOD

	@Override
	public byte[] generatePayslip(Long employeeId, String month) throws Exception {

		// Payroll generate karo
		Payroll payroll = generatePayroll(employeeId, month);

		Employee employee = payroll.getEmployee();

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		Document document = new Document();

		PdfWriter.getInstance(document, out);

		document.open();

		// Title
		Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);

		Paragraph title = new Paragraph("EMPLOYEE PAYSLIP", titleFont);

		title.setAlignment(Element.ALIGN_CENTER);

		document.add(title);

		document.add(new Paragraph(" "));

		LocalDate date = payroll.getGeneratedDate();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		String formattedDate = date.format(formatter);

		// Employee Details
		document.add(new Paragraph("Employee ID : " + employee.getId()));

		document.add(new Paragraph("Employee Name : " + employee.getFirstName() + " " + employee.getLastName()));

		document.add(new Paragraph("Email : " + employee.getEmail()));

		document.add(new Paragraph("Department : " + employee.getDepartment().getDepartmentName()));

		document.add(new Paragraph("Month : " + payroll.getMonth()));

		document.add(new Paragraph("Generated Date : " + formattedDate));

		document.add(new Paragraph(" "));

		// Salary Table
		PdfPTable table = new PdfPTable(2);

		table.setWidthPercentage(100);

		table.addCell("Gross Salary");
		table.addCell(String.valueOf(payroll.getGrossSalary()));

		table.addCell("Leave Deduction");
		table.addCell(String.valueOf(payroll.getLeaveDeduction()));

		table.addCell("Net Salary");
		table.addCell(String.valueOf(payroll.getNetSalary()));

		document.add(table);

		document.add(new Paragraph(" "));
		document.add(new Paragraph("This is system generated payslip."));

		document.close();

		return out.toByteArray();
	}

}