package au.com.myob.payslip.calcuation;

import java.math.BigDecimal;
import java.util.OptionalLong;
import java.util.stream.LongStream;

import au.com.myob.payslip.model.EmployeeMonthlyPayRecord;
import au.com.myob.payslip.model.EmployeeSalaryRecord;

public class MonthlyPayCalculator {

	private static final int FIRST_LEVEL = 18200;
	private static final BigDecimal MONTHS = new BigDecimal(12);

	public static EmployeeMonthlyPayRecord doCalculation(EmployeeSalaryRecord salaryRecord) {
		String name = getName(salaryRecord);
		String grossIncome = getGrossIncome(salaryRecord);
		
		return null;
	}

	public static String getName(EmployeeSalaryRecord salaryRecord) {
		StringBuilder buf = new StringBuilder(salaryRecord.getFirstName());
		buf.append(" ");
		buf.append(salaryRecord.getLastName());
		return buf.toString();
	}

	public static String getGrossIncome(EmployeeSalaryRecord salaryRecord) {
		BigDecimal salary = new BigDecimal(salaryRecord.getAnnualSalary());
		BigDecimal grossIncome = salary.divide(MONTHS, BigDecimal.ROUND_HALF_UP);
		return grossIncome.toString();
	}

	public static String getIncomeTax(EmployeeSalaryRecord salaryRecord) {
		long incomeTax = 0;
		long salary = Long.parseLong(salaryRecord.getAnnualSalary());
		if (FIRST_LEVEL < salary ) {
			if (isInSecondLevel(salary)) {
				incomeTax = doSecondLevelIncomeTaxCalculation(salary);
			}
		}
		return String.valueOf(incomeTax);
	}

	public static long doSecondLevelIncomeTaxCalculation(long salary) {
		BigDecimal taxable = new BigDecimal(salary - FIRST_LEVEL);
		BigDecimal totalIncomeTax = taxable.multiply(new BigDecimal(0.19));
		BigDecimal incomeTax = totalIncomeTax.divide(MONTHS, BigDecimal.ROUND_HALF_UP);
		return incomeTax.longValue();
	}

	public static boolean isInSecondLevel(long salary) {
		OptionalLong containsValue = LongStream.rangeClosed(18201, 37000)
	            .filter(p -> p == salary).findAny();
		return containsValue.isPresent() ? true : false;
	}

}
