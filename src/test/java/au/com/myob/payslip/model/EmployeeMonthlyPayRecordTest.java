package au.com.myob.payslip.model;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import au.com.myob.payslip.fixture.EmployeeMonthlyPayRecordFixture;

/**
 * Test class for EmployeeSalaryRecord
 */
public class EmployeeMonthlyPayRecordTest {

	/**
	 * Given all parameters provided
	 * When the constructor called
	 * Then the object should return
	 */
	@Test
	public void whenAllParametersProvidedThenObjectShouldReturn() {
		//Given all parameters provided
		EmployeeMonthlyPayRecord record = EmployeeMonthlyPayRecordFixture.getDefaultEmployeeMonthlyPayRecord();
		
		//Then the object should return
		assertNotNull(record);
	}
}
