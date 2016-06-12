package au.com.myob.payslip.calcuation;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import au.com.myob.payslip.comm.Constants.Numeral;
import au.com.myob.payslip.model.EmployeeMonthlyPayRecord;
import au.com.myob.payslip.model.EmployeeSalaryRecord;

/**
 * The calculator for monthly pay.
 * 
 * For detail requirements, please read the README.md
 *
 */
public class MonthlyPayCalculator {

	
	private static final String EMPTY = "";
	private static final String PERCENTAGE_SIGN = "%";
	private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
	private static final BigDecimal FOURTH_LEVEL_TAX_AMOUNT = new BigDecimal(54547);
	private static final BigDecimal FIFTH_LEVEL_TAX_RATE = new BigDecimal(0.45);
	private static final BigDecimal THIRD_LEVEL_TAX_AMOUNT = new BigDecimal(17547);
	private static final BigDecimal FOURTH_LEVEL_TAX_RATE = new BigDecimal(0.37);
	private static final BigDecimal SECOND_LEVEL_TAX_AMOUNT = new BigDecimal(3572);
	private static final BigDecimal THIRD_LEVEL_TAX_RATE = new BigDecimal(0.325);
	private static final BigDecimal SECOND_LEVEL_TAX_RATE = new BigDecimal(0.19);
	private static final BigDecimal MONTHS = new BigDecimal(12);
	private static final int FOURTH_LEVEL_END = 180000;
	private static final int FOURTH_LEVEL_START = 80001;
	private static final int THIRD_LEVEL_END = 80000;
	private static final int THIRD_LEVEL_START = 37001;
	private static final int SECOND_LEVEL_END = 37000;
	private static final int SECOND_LEVEL_START = 18201;
	private static final int FIRST_LEVEL = 18200;

	/**
	 * The method constructs the pay record via assemble all needed
	 * information from respected methods.
	 * 
	 * @param salaryRecord the salary record.
	 * @return the pay record.
	 */
	public static EmployeeMonthlyPayRecord doCalculation(EmployeeSalaryRecord salaryRecord) {
		String name = getName(salaryRecord);
		String grossIncome = getGrossIncome(salaryRecord);
		String incomeTax = getIncomeTax(salaryRecord);
		String netIncome = getNetIncome(grossIncome, incomeTax);
		String superContribution = getSuper(grossIncome, salaryRecord);
		
		return new EmployeeMonthlyPayRecord(name, salaryRecord.getPayPeriod(),
				grossIncome, incomeTax, netIncome, superContribution);
	}

	public static String getNetIncome(String grossIncomeString, String incomeTaxString) {
		long netIncome = Numeral.ZERO;
		
		try {
			long grossIncome = Long.parseLong(grossIncomeString);
			long incomeTax = Long.parseLong(incomeTaxString);
			netIncome = grossIncome - incomeTax;
			if (netIncome < Numeral.ZERO) {
				//Negative net income
				netIncome = Numeral.ZERO;
			}
		}
		catch (Exception e) {
			//Ignore the exception 
			//Leave the net income as ZERO.
		}
		return String.valueOf(netIncome);
	}

	public static String getName(EmployeeSalaryRecord salaryRecord) {
		StringBuilder buf = new StringBuilder(salaryRecord.getFirstName());
		buf.append(" ");
		buf.append(salaryRecord.getLastName());
		return buf.toString();
	}

	public static String getGrossIncome(EmployeeSalaryRecord salaryRecord) {
		BigDecimal salary = new BigDecimal(salaryRecord.getAnnualSalary());
		BigDecimal grossIncome = salary.divide(MONTHS, Numeral.ZERO, BigDecimal.ROUND_HALF_UP).setScale(Numeral.ZERO, BigDecimal.ROUND_HALF_UP);
		return grossIncome.toString();
	}

	/**
	 * This method calculates the income tax according to salary record as follow:
	 * 
	 * Taxable income           Tax on this income
	 *	0 - $18,200             Nil
	 *	$18,201 - $37,000       19c for each $1 over $18,200
	 *  $37,001 - $80,000       $3,572 plus 32.5c for each $1 over $37,000
	 *  $80,001 - $180,000      $17,547 plus 37c for each $1 over $80,000
	 *  $180,001 and over       $54,547 plus 45c for each $1 over $180,000
	 *
	 * @param salaryRecord the salary record.
	 * @return the income tax for this salary record
	 */
	public static String getIncomeTax(EmployeeSalaryRecord salaryRecord) {
		long incomeTax = 0;
		long salary = Long.parseLong(salaryRecord.getAnnualSalary());
		if (FIRST_LEVEL < salary ) {
			if (isInSecondLevel(salary)) {
				incomeTax = doSecondLevelIncomeTaxCalculation(salary);
			}
			else if(isInThirdLevel(salary)) {
				incomeTax = doThirdLevelIncomeTaxCalculation(salary);
			}
			else if(isInFourthLevel(salary)){
				incomeTax = doFourthLevelIncomeTaxCalculation(salary);
			}
			else {
				incomeTax = doFifthLevelIncomeTaxCalculation(salary);
			}
		}
		return String.valueOf(incomeTax);
	}

	/**
	 * This method performs income tax calculation for income of
	 * $37,001 - $80,000
	 * 
	 * @param salary the actual salary.
	 * @return income tax for input salary
	 */
	public static long doSecondLevelIncomeTaxCalculation(long salary) {
		BigDecimal taxable = new BigDecimal(salary - FIRST_LEVEL);
		BigDecimal totalIncomeTax = taxable.multiply(SECOND_LEVEL_TAX_RATE);
		BigDecimal incomeTax = totalIncomeTax.divide(MONTHS, Numeral.ZERO, BigDecimal.ROUND_HALF_UP).setScale(Numeral.ZERO, BigDecimal.ROUND_HALF_UP);
		return incomeTax.longValue();
	}

	public static boolean isInSecondLevel(long salary) {
		return (SECOND_LEVEL_START <= salary) && (salary <= SECOND_LEVEL_END);
	}

	public static boolean isInThirdLevel(long salary) {
		return (THIRD_LEVEL_START <= salary) && (salary <= THIRD_LEVEL_END);
	}

	/**
	 * This method performs income tax calculation for income of
	 * $18,201 - $37,000
	 * 
	 * @param salary the actual salary.
	 * @return income tax for input salary
	 */
	public static long doThirdLevelIncomeTaxCalculation(long salary) {
		BigDecimal taxable = new BigDecimal(salary - SECOND_LEVEL_END);
		BigDecimal thirdLevelIncomeTax = taxable.multiply(THIRD_LEVEL_TAX_RATE);
		BigDecimal totalIncomeTax = thirdLevelIncomeTax.add(SECOND_LEVEL_TAX_AMOUNT);
		BigDecimal incomeTax = totalIncomeTax.divide(MONTHS, Numeral.ZERO, BigDecimal.ROUND_HALF_UP).setScale(Numeral.ZERO, BigDecimal.ROUND_HALF_UP);
		return incomeTax.longValue();
	}

	public static boolean isInFourthLevel(long salary) {
		return (FOURTH_LEVEL_START <= salary) && (salary <= FOURTH_LEVEL_END);
	}

	/**
	 * This method performs income tax calculation for income of
	 * $80,001 - $180,000
	 * 
	 * @param salary the actual salary.
	 * @return income tax for input salary
	 */
	public static long doFourthLevelIncomeTaxCalculation(long salary) {
		BigDecimal taxable = new BigDecimal(salary - THIRD_LEVEL_END);
		BigDecimal fourthLevelIncomeTax = taxable.multiply(FOURTH_LEVEL_TAX_RATE);
		BigDecimal totalIncomeTax = fourthLevelIncomeTax.add(THIRD_LEVEL_TAX_AMOUNT);
		BigDecimal incomeTax = totalIncomeTax.divide(MONTHS, Numeral.ZERO, BigDecimal.ROUND_HALF_UP).setScale(Numeral.ZERO, BigDecimal.ROUND_HALF_UP);
		return incomeTax.longValue();
	}

	/**
	 * This method performs income tax calculation for income of
	 * $180,001 and over
	 * 
	 * @param salary the actual salary.
	 * @return income tax for input salary
	 */
	public static long doFifthLevelIncomeTaxCalculation(long salary) {
		BigDecimal taxable = new BigDecimal(salary - FOURTH_LEVEL_END);
		BigDecimal fifthLevelIncomeTax = taxable.multiply(FIFTH_LEVEL_TAX_RATE);
		BigDecimal totalIncomeTax = fifthLevelIncomeTax.add(FOURTH_LEVEL_TAX_AMOUNT);
		BigDecimal incomeTax = totalIncomeTax.divide(MONTHS, Numeral.ZERO, BigDecimal.ROUND_HALF_UP).setScale(Numeral.ZERO, BigDecimal.ROUND_HALF_UP);
		return incomeTax.longValue();
	}

	public static String getSuper(String grossIncomeString, EmployeeSalaryRecord salaryRecord) {
		BigDecimal superContribution = BigDecimal.ZERO;
		
		try {
//			long grossIncome = Long.parseLong(grossIncomeString);
			String superRateString = salaryRecord.getSuperRate();
			if (StringUtils.isNoneBlank(superRateString)) {
				superRateString = superRateString.trim();
				if (superRateString.endsWith(PERCENTAGE_SIGN)) {
					superRateString = superRateString.replace(PERCENTAGE_SIGN, EMPTY);
				}
				BigDecimal superRate = new BigDecimal(superRateString);
				superRate = superRate.divide(ONE_HUNDRED);
				BigDecimal grossIncome = new BigDecimal(grossIncomeString);
				superContribution = grossIncome.multiply(superRate).setScale(Numeral.TWO, BigDecimal.ROUND_HALF_UP);
			}
		}
		catch (Exception e) {
			//Ignore exception and let super contribution as ZERO
		}
		
		return superContribution.toString();
	}

}
