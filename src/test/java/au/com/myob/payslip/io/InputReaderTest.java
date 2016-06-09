package au.com.myob.payslip.io;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import au.com.myob.payslip.EmployeePayslip;
import au.com.myob.payslip.model.EmployeeSalaryRecord;

/**
 * Test class for InputReader
 */
public class InputReaderTest {

	/**
	 * Given all parameters provided
	 * When the constructor called
	 * Then InputReader should exist
	 */
	@Test
	public void whenAllParamsProvidedThenInputStreamExist() {
		InputReader ir = givenInputReader();
		
		//Then inputStream exist
		assertNotNull(ir);
	}

	/* Given all parameters provided
	 * When the read method called
	 * Then InputStream should exist
	 */
	@Test
	public void whenAllParamsProvidedThenContentShouldReturn() throws IOException {
		//Given inputReader
		InputReader ir = givenInputReader();
		
		//When read method called
		List<EmployeeSalaryRecord> content = ir.read();
		//Then content should return
		assertNotNull(content);
		assertTrue(2 == content.size());
	}

	private InputReader givenInputReader() {
		//Given all parameters
		String fileName = EmployeePayslip.DEFAULT_INPUT_FILE;
		int recordSize = Integer.parseInt(EmployeePayslip.DEFAULT_RECORD_SIZE);
		
		//When constructor called
		InputReader ir = new InputReader(fileName, recordSize);
		return ir;
	}
}
