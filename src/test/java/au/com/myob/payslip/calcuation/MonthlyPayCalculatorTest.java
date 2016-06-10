package au.com.myob.payslip.calcuation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.com.myob.payslip.fixture.EmployeeSalaryRecordFixture;
import au.com.myob.payslip.model.EmployeeMonthlyPayRecord;
import au.com.myob.payslip.model.EmployeeSalaryRecord;

/**
 * Test class for MonthlyPayCalculator
 */
public class MonthlyPayCalculatorTest {

	/**
	 * Give a salary record of 60050 
	 * When the doCalculation method called
	 * Then the correct pay record will return
	 */
	@Test
	public void whenSixtyThousandIncomeProvidedThenCorrectRecordShouldReturn() {
		//Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.getDefaultRecord();
		//When the doCalculation method called
		EmployeeMonthlyPayRecord payRecord = MonthlyPayCalculator.doCalculation(salaryRecord);
		//Then correct pay record will return
		assertNotNull(payRecord);
	}
	
	/**
	 * Given a salary record
	 * When the getName method called
	 * Then a string with "firstName lastName" format should return
	 */
	@Test
	public void whenASalaryRecordProvideThenANameShouldBeReturn() {
		//Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.getDefaultRecord();
		//When the getName method called
		String name = MonthlyPayCalculator.getName(salaryRecord);
		//Then a string should return
		assertNotNull(name);
		assertEquals("David Rudd", name);
	}
	
	/**
	 * Given a salary record
	 * When the getGrossIncome method called
	 * Then a string with gross income should return
	 */
	@Test
	public void whenASalaryRecordProvideThenGrossIncomeShouldBeReturn() {
		//Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.getDefaultRecord();
		//When the getName method called
		String grossIncome = MonthlyPayCalculator.getGrossIncome(salaryRecord);
		//Then a string should return
		assertNotNull(grossIncome);
		assertEquals("5004", grossIncome);
	}
	
	/**
	 * Given a salary record
	 * When the getIncomeTax method called
	 * Then a string with income tax should return
	 */
	@Test
	public void whenASalaryRecordProvideThenIncomeTaxShouldBeReturn() {
		//Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.getDefaultRecord();
		//When the getName method called
		String incomeTax = MonthlyPayCalculator.getIncomeTax(salaryRecord);
		//Then a string should return
		assertNotNull(incomeTax);
		assertEquals("922", incomeTax);
	}
	
	/**
	 * Given a 18200 salary record
	 * When the getIncomeTax method called
	 * Then a string with income tax should return
	 */
	@Test
	public void whenA18200SalaryRecordProvideThenIncomeTaxShouldBeReturn() {
		//Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.get18200Record();
		//When the getName method called
		String incomeTax = MonthlyPayCalculator.getIncomeTax(salaryRecord);
		//Then a string should return
		assertNotNull(incomeTax);
		assertEquals("0", incomeTax);
	}
	
	/**
	 * Given a 80000 salary record
	 * When the getIncomeTax method called
	 * Then a string with income tax should return
	 */
	@Test
	public void whenA80000SalaryRecordProvideThenIncomeTaxShouldBeReturn() {
		//Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.get80000Record();
		//When the getName method called
		String incomeTax = MonthlyPayCalculator.getIncomeTax(salaryRecord);
		//Then a string should return
		assertNotNull(incomeTax);
		assertEquals("3572", incomeTax);
	}
	
	/**
	 * Given a 37000 salary record
	 * When the isInSecondLevel method called
	 * Then true should return
	 */
	@Test
	public void whenA37000SalaryRecordProvideThenTrueShouldBeReturn() {
		//Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.get37000Record();
		long salary = Long.parseLong(salaryRecord.getAnnualSalary());
		//When the getName method called
		boolean flag = MonthlyPayCalculator.isInSecondLevel(salary);
		//Then a true should return
		assertTrue(flag);
	}
}
