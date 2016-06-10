package au.com.myob.payslip.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import au.com.myob.payslip.fixture.EmployeeMonthlyPayRecordFixture;
import au.com.myob.payslip.model.EmployeeMonthlyPayRecord;

/**
 * Test class for OutputWriter
 */
public class OutputWriterTest {

	private static final String TEST_OUTPUT_FILE = "outputFile";

	/**
	 * Given the file name When the constructor called Then object should return
	 */
	@Test
	public void whenFileNameProvidedThenObjectShouldBeReturn() {
		// Given the writer
		OutputWriter writer = givenWriter();
		// Then object should return
		assertNotNull(writer);
	}

	/**
	 * Given a list of records When the write method called Then data should be
	 * written to file
	 * 
	 * @throws IOException
	 */
	@Test
	public void whenListOfRecordsProvidedThenFileShouldBePopulate() throws IOException {
		Path path = null;

		try {
			// Given the writer
			OutputWriter writer = givenWriter();
			EmployeeMonthlyPayRecord record = EmployeeMonthlyPayRecordFixture.getDefaultEmployeeMonthlyPayRecord();
			List<EmployeeMonthlyPayRecord> records = new ArrayList<>();
			records.add(record);
			// When the write method called
			writer.write(records);
			// Then the file should populated
			path = Paths.get(TEST_OUTPUT_FILE);
			List<String> retrieved = Files.readAllLines(path);
			assertNotNull(retrieved);
			assertTrue(1 == retrieved.size());
			assertEquals(record.toString(), retrieved.get(0).toString());
		} finally {
			if (null != path)
				Files.delete(path);
		}
	}

	private OutputWriter givenWriter() {
		// Given the output file name
		String outputFile = TEST_OUTPUT_FILE;
		// When the constructor called
		return new OutputWriter(outputFile);
	}
}
