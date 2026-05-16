package com.nexusHr.payrollService.service;

import java.util.List;

import com.nexusHr.payrollService.entity.Payroll;

public interface PayrollService {

	Payroll generatePayroll(Long employeeId, String month);

	Payroll updatePayroll(Long id, Payroll payroll);

	void deletePayroll(Long id);

	List<Payroll> getAllPayrolls();

	Payroll getPayrollById(Long id);

	public byte[] generatePayslip(Long employeeId, String month) throws Exception;
}
