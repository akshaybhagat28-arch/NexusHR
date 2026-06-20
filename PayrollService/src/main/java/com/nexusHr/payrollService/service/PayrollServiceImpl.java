package com.nexusHr.payrollService.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
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
import java.time.Month;
import java.time.YearMonth;
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

	    Employee employee =
	            employeeRepository.findById(employeeId)
	            .orElseThrow(() ->
	                    new RuntimeException("Employee not found"));

	    int monthNumber =
	            Month.valueOf(month.toUpperCase()).getValue();

	    int year = LocalDate.now().getYear();

	    // Total days in selected month
	    int totalWorkingDays =
	            YearMonth.of(year, monthNumber).lengthOfMonth();

	    // Approved leaves
	    List<LeaveRequest> leaves =
	            leaveRepository.getApprovedLeavesByMonth(
	                    employeeId,
	                    monthNumber);

	    int leavesTaken = leaves.stream()
	            .mapToInt(LeaveRequest::getTotalDays)
	            .sum();

	    // Salary
	    double grossSalary = employee.getBasicSalary();

	    // Per day salary
	    double perDaySalary =
	            grossSalary / totalWorkingDays;

	    // Deduction
	    double leaveDeduction =
	            perDaySalary * leavesTaken;

	    // Net salary
	    double netSalary =
	            grossSalary - leaveDeduction;

	    Payroll payroll = new Payroll();

	    payroll.setEmployee(employee);

	    payroll.setMonth(month);

	    payroll.setGeneratedDate(LocalDate.now());

	    payroll.setGrossSalary(grossSalary);

	    payroll.setLeaveDeduction(
	            (double) Math.round(leaveDeduction));

	    payroll.setNetSalary(
	            (double) Math.round(netSalary));

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

		// Payroll generate
		Payroll payroll = generatePayroll(employeeId, month);

		Employee employee = payroll.getEmployee();

		// Month convert
		int monthNumber = Month.valueOf(month.toUpperCase()).getValue();

		int year = LocalDate.now().getYear();

		// Total working days
		int totalWorkingDays = YearMonth.of(year, monthNumber).lengthOfMonth();

		// Approved leaves of selected month
		List<LeaveRequest> leaves = leaveRepository.getApprovedLeavesByMonth(employeeId, monthNumber);

		// Total leaves taken
		int leavesTaken = leaves.stream().mapToInt(LeaveRequest::getTotalDays).sum();

		// Present days
		int presentDays = totalWorkingDays - leavesTaken;

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		Document document = new Document();

		PdfWriter.getInstance(document, out);

		document.open();

		// ===== TITLE =====

		Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);

		Paragraph title = new Paragraph("EMPLOYEE PAYSLIP", titleFont);

		title.setAlignment(Element.ALIGN_CENTER);

		document.add(title);

		document.add(new Paragraph(" "));

		// ===== DATE =====

		LocalDate date = payroll.getGeneratedDate();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		String formattedDate = date.format(formatter);

		// ===== EMPLOYEE DETAILS =====

		Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);

		document.add(new Paragraph("Employee Details", headingFont));

		document.add(new Paragraph(" "));

		document.add(new Paragraph("Employee ID : " + employee.getId()));

		document.add(new Paragraph("Employee Name : " + employee.getFirstName() + " " + employee.getLastName()));

		document.add(new Paragraph("Email : " + employee.getEmail()));

		document.add(new Paragraph("Department : " + employee.getDepartment().getDepartmentName()));

		document.add(new Paragraph("Month : " + payroll.getMonth()));

		document.add(new Paragraph("Generated Date : " + formattedDate));

		document.add(new Paragraph(" "));

		// ===== ATTENDANCE DETAILS =====

		document.add(new Paragraph("Attendance Details", headingFont));

		document.add(new Paragraph(" "));

		document.add(new Paragraph("Total Working Days : " + totalWorkingDays));

		document.add(new Paragraph("Leaves Taken : " + leavesTaken));

		document.add(new Paragraph("Present Days : " + presentDays));

		document.add(new Paragraph(" "));

		// ===== SALARY TABLE =====

		document.add(new Paragraph("Salary Details", headingFont));

		document.add(new Paragraph(" "));

		PdfPTable table = new PdfPTable(2);

		table.setWidthPercentage(100);

		table.setSpacingBefore(10f);

		table.setSpacingAfter(10f);

		// Header Font
		Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

		PdfPCell cell1 = new PdfPCell(new Phrase("Description", headerFont));

		PdfPCell cell2 = new PdfPCell(new Phrase("Amount", headerFont));

		table.addCell(cell1);
		table.addCell(cell2);

		// Salary Rows

		table.addCell("Gross Salary");
		table.addCell(String.valueOf(payroll.getGrossSalary()));

		table.addCell("Leave Deduction");
		table.addCell(String.valueOf(payroll.getLeaveDeduction()));

		table.addCell("Net Salary");
		table.addCell(String.valueOf(payroll.getNetSalary()));

		document.add(table);

		document.add(new Paragraph(" "));

		// ===== FOOTER =====

		Font footerFont = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10);

		Paragraph footer = new Paragraph("This is system generated payslip.", footerFont);

		footer.setAlignment(Element.ALIGN_CENTER);

		document.add(footer);

		document.close();

		return out.toByteArray();
	}

}