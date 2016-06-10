package au.com.myob.payslip.fixture;

import au.com.myob.payslip.model.EmployeeSalaryRecord;

public class EmployeeSalaryRecordFixture {

	private static String firstName;
	private static String lastName;
	private static String annualSalary;
	private static String superRate;
	private static String payPeriod;

	public static EmployeeSalaryRecord getDefaultRecord() {
		// Given all parameters provided
		// When the constructor called
		EmployeeSalaryRecord record = new EmployeeSalaryRecord(firstName, lastName, annualSalary, superRate, payPeriod);
		return record;
	}

	public static EmployeeSalaryRecord get18200Record() {
		// Given all parameters provided
		String annualSalary = "18200";
		// When the constructor called
		EmployeeSalaryRecord record = new EmployeeSalaryRecord(firstName, lastName, annualSalary, superRate, payPeriod);
		return record;
	}

	public static EmployeeSalaryRecord get80000Record() {
		// Given all parameters provided
		String annualSalary = "80000";
		
		// When the constructor called
		EmployeeSalaryRecord record = new EmployeeSalaryRecord(firstName, lastName, annualSalary, superRate, payPeriod);
		return record;
	}

	public static EmployeeSalaryRecord get37000Record() {
		// Given all parameters provided
		
		annualSalary = "37000";
		
		// When the constructor called
		EmployeeSalaryRecord record = new EmployeeSalaryRecord(firstName, lastName, annualSalary, superRate, payPeriod);
		return record;
	}

	static {
		firstName = "David";
		lastName = "Rudd";
		superRate = "9%";
		payPeriod = "01 March - 31 March";
		annualSalary = "60050";
	}
}
