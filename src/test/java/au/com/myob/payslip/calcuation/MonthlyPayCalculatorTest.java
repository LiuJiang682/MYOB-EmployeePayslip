package au.com.myob.payslip.calcuation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import au.com.myob.payslip.fixture.EmployeeSalaryRecordFixture;
import au.com.myob.payslip.model.EmployeeMonthlyPayRecord;
import au.com.myob.payslip.model.EmployeeSalaryRecord;

/**
 * Test class for MonthlyPayCalculator
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(MonthlyPayCalculator.class)
public class MonthlyPayCalculatorTest {

	private static final String DEFAULT_SUPER_CONTRIBUTION = "450.36";

	/**
	 * Give a salary record of 60050 
	 * When the doCalculation method called 
	 * Then the correct pay record will return
	 */
	@Test
	public void whenSixtyThousandIncomeProvidedThenCorrectRecordShouldReturn() {
		// Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.getDefaultRecord();
		// When the doCalculation method called
		EmployeeMonthlyPayRecord payRecord = MonthlyPayCalculator.doCalculation(salaryRecord);
		// Then correct pay record will return
		assertNotNull(payRecord);
	}

	/**
	 * Given a salary record 
	 * When the getName method called 
	 * Then a string with "firstName lastName" format should return
	 */
	@Test
	public void whenASalaryRecordProvideThenANameShouldBeReturn() {
		// Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.getDefaultRecord();
		// When the getName method called
		String name = MonthlyPayCalculator.getName(salaryRecord);
		// Then a string should return
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
		// Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.getDefaultRecord();
		// When the getName method called
		String grossIncome = MonthlyPayCalculator.getGrossIncome(salaryRecord);
		// Then a string should return
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
		// Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.getDefaultRecord();
		// When the getName method called
		String incomeTax = MonthlyPayCalculator.getIncomeTax(salaryRecord);
		// Then a string should return
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
		// Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.get18200Record();
		// When the getName method called
		String incomeTax = MonthlyPayCalculator.getIncomeTax(salaryRecord);
		// Then a string should return
		assertNotNull(incomeTax);
		assertEquals("0", incomeTax);
	}

	/**
	 * Given a 37000 salary record 
	 * When the getIncomeTax method called 
	 * Then a string with income tax should return
	 * 
	 * @throws Exception
	 */
	@Test
	public void whenA37000SalaryRecordProvideThenIncomeTaxShouldBeReturn() throws Exception {
		// Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.get37000Record();
		PowerMockito.mockStatic(MonthlyPayCalculator.class);
		PowerMockito.doCallRealMethod().when(MonthlyPayCalculator.class, "getIncomeTax",
				Matchers.any(EmployeeSalaryRecord.class));
		PowerMockito.doReturn(true).when(MonthlyPayCalculator.class, "isInSecondLevel", Matchers.anyLong());
		PowerMockito.doReturn(new Long(298)).when(MonthlyPayCalculator.class, "doSecondLevelIncomeTaxCalculation",
				Matchers.anyLong());
		// When the getName method called
		String incomeTax = MonthlyPayCalculator.getIncomeTax(salaryRecord);
		// Then a string should return
		assertNotNull(incomeTax);
		assertEquals("298", incomeTax);
	}

	/**
	 * Given a 80000 salary record 
	 * When the getIncomeTax method called 
	 * Then a string with income tax should return
	 * 
	 * @throws Exception
	 */
	@Test
	public void whenA80000SalaryRecordProvideThenIncomeTaxShouldBeReturn() throws Exception {
		// Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.get80000Record();
		PowerMockito.mockStatic(MonthlyPayCalculator.class);
		PowerMockito.doCallRealMethod().when(MonthlyPayCalculator.class, "getIncomeTax",
				Matchers.any(EmployeeSalaryRecord.class));
		PowerMockito.doReturn(true).when(MonthlyPayCalculator.class, "isInThirdLevel", Matchers.anyLong());
		PowerMockito.doReturn(new Long(1462)).when(MonthlyPayCalculator.class, "doThirdLevelIncomeTaxCalculation",
				Matchers.anyLong());
		// When the getName method called
		String incomeTax = MonthlyPayCalculator.getIncomeTax(salaryRecord);
		// Then a string should return
		assertNotNull(incomeTax);
		assertEquals("1462", incomeTax);
	}

	/**
	 * Given a 180000 salary record 
	 * When the getIncomeTax method called 
	 * Then a string with income tax should return
	 * 
	 * @throws Exception
	 */
	@Test
	public void whenA180000SalaryRecordProvideThenIncomeTaxShouldBeReturn() throws Exception {
		// Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.get180000Record();
		PowerMockito.mockStatic(MonthlyPayCalculator.class);
		PowerMockito.doCallRealMethod().when(MonthlyPayCalculator.class, "getIncomeTax",
				Matchers.any(EmployeeSalaryRecord.class));
		PowerMockito.doReturn(true).when(MonthlyPayCalculator.class, "isInFourthLevel", Matchers.anyLong());
		PowerMockito.doReturn(new Long(4546)).when(MonthlyPayCalculator.class, "doFourthLevelIncomeTaxCalculation",
				Matchers.anyLong());
		// When the getName method called
		String incomeTax = MonthlyPayCalculator.getIncomeTax(salaryRecord);
		// Then a string should return
		assertNotNull(incomeTax);
		assertEquals("4546", incomeTax);
	}

	/**
	 * Given a 37000 salary record 
	 * When the isInSecondLevel method called 
	 * Then true should return
	 */
	@Test
	public void whenA37000SalaryRecordProvideThenTrueShouldBeReturn() {
		// Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.get37000Record();
		long salary = Long.parseLong(salaryRecord.getAnnualSalary());
		// When the getName method called
		boolean flag = MonthlyPayCalculator.isInSecondLevel(salary);
		// Then a true should return
		assertTrue(flag);
	}

	/**
	 * Given a 37000 salary record 
	 * When the doSecondLevelIncomeTaxCalculation method called 
	 * Then 298 should return
	 */
	@Test
	public void whenA37000SalaryRecordProvideThen298ShouldReturn() {
		// Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.get37000Record();
		long salary = Long.parseLong(salaryRecord.getAnnualSalary());
		// When the getName method called
		long incomeTax = MonthlyPayCalculator.doSecondLevelIncomeTaxCalculation(salary);
		// Then a true should return
		assertTrue(298 == incomeTax);
	}

	/**
	 * Given a 37000 salary record 
	 * When the isInSecondLevel method called 
	 * Then true should return
	 */
	@Test
	public void whenA80000SalaryRecordProvideThenTrueShouldBeReturn() {
		// Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.get80000Record();
		long salary = Long.parseLong(salaryRecord.getAnnualSalary());
		// When the getName method called
		boolean flag = MonthlyPayCalculator.isInThirdLevel(salary);
		// Then a true should return
		assertTrue(flag);
	}

	/**
	 * Given a 80000 salary record 
	 * When the doThirdLevelIncomeTaxCalculation method called 
	 * Then 1462 should return
	 */
	@Test
	public void whenA37000SalaryRecordProvideThen1462ShouldReturn() {
		// Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.get80000Record();
		long salary = Long.parseLong(salaryRecord.getAnnualSalary());
		// When the getName method called
		long incomeTax = MonthlyPayCalculator.doThirdLevelIncomeTaxCalculation(salary);
		// Then a true should return
		assertTrue(1462 == incomeTax);
	}

	/**
	 * Given a 180000 salary record 
	 * When the isInFourthLevel method called 
	 * Then true should return
	 */
	@Test
	public void whenA180000SalaryRecordProvideThenTrueShouldBeReturn() {
		// Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.get180000Record();
		long salary = Long.parseLong(salaryRecord.getAnnualSalary());
		// When the getName method called
		boolean flag = MonthlyPayCalculator.isInFourthLevel(salary);
		// Then a true should return
		assertTrue(flag);
	}

	/**
	 * Given a 180000 salary record 
	 * When the doFourthLevelIncomeTaxCalculation method called 
	 * Then 4546 should return
	 */
	@Test
	public void whenA180000SalaryRecordProvideThen4546ShouldReturn() {
		// Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.get180000Record();
		long salary = Long.parseLong(salaryRecord.getAnnualSalary());
		// When the getName method called
		long incomeTax = MonthlyPayCalculator.doFourthLevelIncomeTaxCalculation(salary);
		// Then a true should return
		assertTrue(4546 == incomeTax);
	}

	/**
	 * Given a 280000 salary record 
	 * When the doFourthLevelIncomeTaxCalculation method called 
	 * Then 4546 should return
	 */
	@Test
	public void whenA280000SalaryRecordProvideThen8296ShouldReturn() {
		// Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.get280000Record();
		long salary = Long.parseLong(salaryRecord.getAnnualSalary());
		// When the getName method called
		long incomeTax = MonthlyPayCalculator.doFifthLevelIncomeTaxCalculation(salary);
		// Then a true should return
		assertTrue(8296 == incomeTax);
	}

	/**
	 * Given a 280000 salary record 
	 * When the getIncomeTax method called 
	 * Then a string with income tax should return
	 * 
	 * @throws Exception
	 */
	@Test
	public void whenA280000SalaryRecordProvideThenIncomeTaxShouldBeReturn() throws Exception {
		// Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.get180000Record();
		PowerMockito.mockStatic(MonthlyPayCalculator.class);
		PowerMockito.doCallRealMethod().when(MonthlyPayCalculator.class, "getIncomeTax",
				Matchers.any(EmployeeSalaryRecord.class));
		PowerMockito.doReturn(new Long(8296)).when(MonthlyPayCalculator.class, "doFifthLevelIncomeTaxCalculation",
				Matchers.anyLong());
		// When the getIncomeTax method called
		String incomeTax = MonthlyPayCalculator.getIncomeTax(salaryRecord);
		// Then a string should return
		assertNotNull(incomeTax);
		assertEquals("8296", incomeTax);
	}

	/**
	 * Given a gross income 5004 and income tax 922 
	 * When the getNetincome method called 
	 * Then 4082 return
	 */
	@Test
	public void whenGrossIncomeNIncomeTaxProvidedThenNetincomeShouldReturn() {
		// Given gross income and income tax
		String grossIncome = "5004";
		String incomeTax = "922";
		// When the getNetincome method called
		String netIncome = MonthlyPayCalculator.getNetIncome(grossIncome, incomeTax);
		// Then 4082 should return
		assertEquals("4082", netIncome);
	}

	/**
	 * Given a gross income 5004 and income tax 922 
	 * When the getNetincome method called in wrong order of parameters 
	 * Then 0 return
	 */
	@Test
	public void whenGrossIncomeNIncomeTaxProvidedInWrongOrderThenNetincomeShouldReturnZERO() {
		// Given gross income and income tax
		String grossIncome = "5004";
		String incomeTax = "922";
		// When the getNetincome method called
		String netIncome = MonthlyPayCalculator.getNetIncome(incomeTax, grossIncome);
		// Then 0 should return
		assertEquals("0", netIncome);
	}

	/**
	 * Given a gross income 5004a and income tax 922 
	 * When the getNetincome method called in wrong order of parameters 
	 * Then 0 return
	 */
	@Test
	public void whenGrossIncomeNIncomeTaxProvidedInvalidThenNetincomeShouldReturnZERO() {
		// Given gross income and income tax
		String grossIncome = "5004a";
		String incomeTax = "922";
		// When the getNetincome method called
		String netIncome = MonthlyPayCalculator.getNetIncome(incomeTax, grossIncome);
		// Then 0 should return
		assertEquals("0", netIncome);
	}

	/**
	 * Given a gross income and salary record 
	 * When the getSuper method called
	 * Then a string return
	 */
	@Test
	public void whenGrossIncomeNSalaryRecordProvidedThenSuperReturn() {
		// Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.getDefaultRecord();
		String grossIncome = "5004";
		//When the getSuper method called
		String superContribution = MonthlyPayCalculator.getSuper(grossIncome, salaryRecord);
		//Then super contribution return
		assertNotNull(superContribution);
		assertEquals(DEFAULT_SUPER_CONTRIBUTION, superContribution);
	}
	
	/**
	 * Given a gross income and salary record with No super rate 
	 * When the getSuper method called
	 * Then a 0 string return
	 */
	@Test
	public void whenNoSuperRateProvidedThenZeroSuperReturn() {
		// Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.getNoSuperRateRecord();
		String grossIncome = "5004";
		//When the getSuper method called
		String superContribution = MonthlyPayCalculator.getSuper(grossIncome, salaryRecord);
		//Then super contribution return
		assertNotNull(superContribution);
		assertEquals("0", superContribution);
	}
	
	/**
	 * Given an invalid gross income and salary record with super rate 
	 * When the getSuper method called
	 * Then a zero string return
	 */
	@Test
	public void whenInvalidGrossIncomeProvidedThenZeroSuperReturn() {
		// Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.getDefaultRecord();
		String grossIncome = "5004a";
		//When the getSuper method called
		String superContribution = MonthlyPayCalculator.getSuper(grossIncome, salaryRecord);
		//Then super contribution return
		assertNotNull(superContribution);
		assertEquals("0", superContribution);
	}
	
	/**
	 * Given a gross income and salary record with super rate 
	 * When the getSuper method called
	 * Then a super contribution string return
	 */
	@Test
	public void whenNoPercentSignSuperRateProvidedThenSuperReturn() {
		// Given a salary record
		EmployeeSalaryRecord salaryRecord = EmployeeSalaryRecordFixture.getSuperNoSignRecord();
		String grossIncome = "5004";
		//When the getSuper method called
		String superContribution = MonthlyPayCalculator.getSuper(grossIncome, salaryRecord);
		//Then super contribution return
		assertNotNull(superContribution);
		assertEquals(DEFAULT_SUPER_CONTRIBUTION, superContribution);
	}
}
