package au.com.myob.payslip.model;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import au.com.myob.payslip.fixture.EmployeeSalaryRecordFixture;

/**
 * Test class for EmployeeSalaryRecord
 */
public class EmployeeSalaryRecordTest {

	/**
	 * Given all parameter provided
	 * When the constructor called
	 * Then the object should return
	 */
	@Test
	public void whenAllParametersProvidedThenObjectShouldReturn() {
		EmployeeSalaryRecord record = EmployeeSalaryRecordFixture.getDefaultRecord();
		//Then object should return
		assertNotNull(record);
	}
}
