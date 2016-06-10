package au.com.myob.payslip.model;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

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
		//Given all parameters provided
		String firstName = "David";
		String lastName = "Rudd";
		String annualSalary = "60050";
		String superRate = "9%";
		String payPeriod = "01 March - 31 March";
		//When the constructor called
		EmployeeSalaryRecord record = new EmployeeSalaryRecord(firstName,
				lastName, annualSalary, superRate, payPeriod);
		//Then object should return
		assertNotNull(record);
	}
}
