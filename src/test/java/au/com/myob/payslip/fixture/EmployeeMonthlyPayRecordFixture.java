package au.com.myob.payslip.fixture;

import java.util.ArrayList;
import java.util.List;

import au.com.myob.payslip.model.EmployeeMonthlyPayRecord;

public class EmployeeMonthlyPayRecordFixture {

	public static EmployeeMonthlyPayRecord getDefaultEmployeeMonthlyPayRecord() {
		String name = "David Rudd";
		String payPeriod = "01 March - 31 March";
		String grossIncome = "5004";
		String incomeTax = "922";
		String netIncome = "4082";
		String superContribution = "450";
		//When the constructor called
		EmployeeMonthlyPayRecord record = new EmployeeMonthlyPayRecord(name,
				payPeriod, grossIncome, incomeTax, netIncome, 
				superContribution);
		return record;
	}

	public static List<EmployeeMonthlyPayRecord> getEmployeeMonthlyPayRecordList() {
		EmployeeMonthlyPayRecord record = getDefaultEmployeeMonthlyPayRecord();
		List<EmployeeMonthlyPayRecord> records = new ArrayList<>();
		records.add(record);
		return records;
	}

	public static EmployeeMonthlyPayRecord getRyanRecord() {
		String name = "Ryan Chen";
		String payPeriod = "01 March - 31 March";
		String grossIncome = "10000";
		String incomeTax = "2696";
		String netIncome = "7301";
		String superContribution = "1000";
		//When the constructor called
		EmployeeMonthlyPayRecord record = new EmployeeMonthlyPayRecord(name,
				payPeriod, grossIncome, incomeTax, netIncome, 
				superContribution);
		return record;
	}
	
	public static List<EmployeeMonthlyPayRecord> get2EmployeeMonthlyPayRecordList() {
		EmployeeMonthlyPayRecord record = getDefaultEmployeeMonthlyPayRecord();
		List<EmployeeMonthlyPayRecord> records = new ArrayList<>();
		records.add(record);
		records.add(getRyanRecord());
		return records;
	}
}
