package au.com.myob.payslip.fixture;

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
}
