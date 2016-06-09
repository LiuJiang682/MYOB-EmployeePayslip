package au.com.myob.payslip;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * In order to fulfill the company's obligation to pay its employee
 * As the CFO of the company
 * I want to generate all employee's monthly payslip
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(EmployeePayslip.class)
public class EmployeePayslipTest {

	private static final String TEST_RECORD_SIZE2 = "68";
	private static final String TEST_RECORD_SIZE = "6000";
	private static final String TEST_OUTPUT_FILE = "def";
	private static final String TEST_INPUT_FILE = "abc";

	/**
	 * Given no input/output files and record size provided
	 * When the application start
	 * Then default parameter will used
	 * @throws Exception 
	 */
	@Test
	public void whenNoParamsThenDefaultWillUse() throws Exception {
		//Given no parameter provided
		String[] params = null;
		EmployeePayslip mockPayslip = PowerMockito.mock(EmployeePayslip.class);
		PowerMockito.whenNew(EmployeePayslip.class).withArguments(Matchers.anyString(), Matchers.anyString(), Matchers.anyString()).thenReturn(mockPayslip);
		
		//When the application start
		EmployeePayslip.main(params);
		//Then default parameter will used
		ArgumentCaptor<String> captor1 = ArgumentCaptor.forClass(String.class);
		verifyNew(EmployeePayslip.class).withArguments(captor1.capture(), captor1.capture(), captor1.capture());
		List<String> strings = captor1.getAllValues();
//		System.out.println(strings);
		String string1 = strings.get(0);
		String string2 = strings.get(1);
		String string3 = strings.get(2);
		assertNotNull(string1);
		assertEquals(EmployeePayslip.DEFAULT_INPUT_FILE, string1);
		assertNotNull(string2);
		assertEquals(EmployeePayslip.DEFAULT_OUTPUT_FILE, string2);
		assertNotNull(string3);
		assertEquals(EmployeePayslip.DEFAULT_RECORD_SIZE, string3);
	}
	
	/**
	 * Given no input/output files and record size provided
	 * When the application start
	 * Then default parameter will used
	 * @throws Exception 
	 */
	@Test
	public void whenEmptyParamsThenDefaultWillUse() throws Exception {
		//Given no parameter provided
		String[] params = {};
		EmployeePayslip mockPayslip = PowerMockito.mock(EmployeePayslip.class);
		PowerMockito.whenNew(EmployeePayslip.class).withArguments(Matchers.anyString(), Matchers.anyString(), Matchers.anyString()).thenReturn(mockPayslip);
		
		//When the application start
		EmployeePayslip.main(params);
		//Then default parameter will used
		ArgumentCaptor<String> captor1 = ArgumentCaptor.forClass(String.class);
		verifyNew(EmployeePayslip.class).withArguments(captor1.capture(), captor1.capture(), captor1.capture());
		List<String> strings = captor1.getAllValues();
//		System.out.println(strings);
		String string1 = strings.get(0);
		String string2 = strings.get(1);
		String string3 = strings.get(2);
		assertNotNull(string1);
		assertEquals(EmployeePayslip.DEFAULT_INPUT_FILE, string1);
		assertNotNull(string2);
		assertEquals(EmployeePayslip.DEFAULT_OUTPUT_FILE, string2);
		assertNotNull(string3);
		assertEquals(EmployeePayslip.DEFAULT_RECORD_SIZE, string3);
	}
	
	/**
	 * Given all input/output files, record size and some rubbish provided
	 * When the application start
	 * Then default parameter will used
	 * @throws Exception 
	 */
	@Test
	public void whenRubbishParamsThenDefaultWillUse() throws Exception {
		//Given no parameter provided
		String[] params = {"", "", "", ""};
		EmployeePayslip mockPayslip = PowerMockito.mock(EmployeePayslip.class);
		PowerMockito.whenNew(EmployeePayslip.class).withArguments(Matchers.anyString(), Matchers.anyString(), Matchers.anyString()).thenReturn(mockPayslip);
		
		//When the application start
		EmployeePayslip.main(params);
		//Then default parameter will used
		ArgumentCaptor<String> captor1 = ArgumentCaptor.forClass(String.class);
		verifyNew(EmployeePayslip.class).withArguments(captor1.capture(), captor1.capture(), captor1.capture());
		List<String> strings = captor1.getAllValues();
//		System.out.println(strings);
		String string1 = strings.get(0);
		String string2 = strings.get(1);
		String string3 = strings.get(2);
		assertNotNull(string1);
		assertEquals(EmployeePayslip.DEFAULT_INPUT_FILE, string1);
		assertNotNull(string2);
		assertEquals(EmployeePayslip.DEFAULT_OUTPUT_FILE, string2);
		assertNotNull(string3);
		assertEquals(EmployeePayslip.DEFAULT_RECORD_SIZE, string3);
	}
	
	/**
	 * Given input files provided
	 * When the application start
	 * Then default output file and record size will used
	 * @throws Exception 
	 */
	@Test
	public void whenOneParamsThenTwoDefaultWillUse() throws Exception {
		//Given input file parameter provided
		String[] params = {TEST_INPUT_FILE};
		EmployeePayslip mockPayslip = PowerMockito.mock(EmployeePayslip.class);
		PowerMockito.whenNew(EmployeePayslip.class).withArguments(Matchers.anyString(), Matchers.anyString(), Matchers.anyString()).thenReturn(mockPayslip);
		
		//When the application start
		EmployeePayslip.main(params);
		//Then default parameter will used
		ArgumentCaptor<String> captor1 = ArgumentCaptor.forClass(String.class);
		verifyNew(EmployeePayslip.class).withArguments(captor1.capture(), captor1.capture(), captor1.capture());
		List<String> strings = captor1.getAllValues();
//		System.out.println(strings);
		String string1 = strings.get(0);
		String string2 = strings.get(1);
		String string3 = strings.get(2);
		assertNotNull(string1);
		assertEquals(TEST_INPUT_FILE, string1);
		assertNotNull(string2);
		assertEquals(EmployeePayslip.DEFAULT_OUTPUT_FILE, string2);
		assertNotNull(string3);
		assertEquals(EmployeePayslip.DEFAULT_RECORD_SIZE, string3);
	}
	
	/**
	 * Given input and output files provided
	 * When the application start
	 * Then default record size will used
	 * @throws Exception 
	 */
	@Test
	public void whenTwoParamsThenOneDefaultWillUse() throws Exception {
		//Given input file parameter provided
		String[] params = {TEST_INPUT_FILE, TEST_OUTPUT_FILE};
		EmployeePayslip mockPayslip = PowerMockito.mock(EmployeePayslip.class);
		PowerMockito.whenNew(EmployeePayslip.class).withArguments(Matchers.anyString(), Matchers.anyString(), Matchers.anyString()).thenReturn(mockPayslip);
		
		//When the application start
		EmployeePayslip.main(params);
		//Then default parameter will used
		ArgumentCaptor<String> captor1 = ArgumentCaptor.forClass(String.class);
		verifyNew(EmployeePayslip.class).withArguments(captor1.capture(), captor1.capture(), captor1.capture());
		List<String> strings = captor1.getAllValues();
//		System.out.println(strings);
		String string1 = strings.get(0);
		String string2 = strings.get(1);
		String string3 = strings.get(2);
		assertNotNull(string1);
		assertEquals(TEST_INPUT_FILE, string1);
		assertNotNull(string2);
		assertEquals(TEST_OUTPUT_FILE, string2);
		assertNotNull(string3);
		assertEquals(EmployeePayslip.DEFAULT_RECORD_SIZE, string3);
	}
	
	/**
	 * Given input, output files and record size provided
	 * When the application start
	 * Then no default record size will used
	 * @throws Exception 
	 */
	@Test
	public void whenAllParamsThenNoDefaultWillUse() throws Exception {
		//Given input file parameter provided
		String[] params = {TEST_INPUT_FILE, TEST_OUTPUT_FILE, TEST_RECORD_SIZE};
		EmployeePayslip mockPayslip = PowerMockito.mock(EmployeePayslip.class);
		PowerMockito.whenNew(EmployeePayslip.class).withArguments(Matchers.anyString(), Matchers.anyString(), Matchers.anyString()).thenReturn(mockPayslip);
		
		//When the application start
		EmployeePayslip.main(params);
		//Then default parameter will used
		ArgumentCaptor<String> captor1 = ArgumentCaptor.forClass(String.class);
		verifyNew(EmployeePayslip.class).withArguments(captor1.capture(), captor1.capture(), captor1.capture());
		List<String> strings = captor1.getAllValues();
//		System.out.println(strings);
		String string1 = strings.get(0);
		String string2 = strings.get(1);
		String string3 = strings.get(2);
		assertNotNull(string1);
		assertEquals(TEST_INPUT_FILE, string1);
		assertNotNull(string2);
		assertEquals(TEST_OUTPUT_FILE, string2);
		assertNotNull(string3);
		assertEquals(TEST_RECORD_SIZE, string3);
	}
	
	/**
	 * Given parameter h provided
	 * When the application start
	 * Then usage will called
	 * @throws Exception 
	 */
	@Test
	public void whenParamsHProvidedThenUsageWillUse() throws Exception {
		//Given parameter h provided
		String[] params = {"h"};
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		System.setOut(ps);
		
		//When the application start
		EmployeePayslip.main(params);
		//Then usage will used
		String content = new String(baos.toByteArray(), StandardCharsets.UTF_8);
		assertFalse(StringUtils.isBlank(content));
		assertEquals(EmployeePayslip.USAGE + "\n", content);
	}
	
	/**
	 * Given all parameters provided
	 * When the constructor called
	 * Then the object should constructed
	 */
	@Test
	public void whenParamsProvidedThenObjectShouldReturn() {
		assertNotNull(new EmployeePayslip(TEST_INPUT_FILE, TEST_OUTPUT_FILE, TEST_RECORD_SIZE));
	}
	
	/**
	 * Given all parameters provided but record size is not a number
	 * When the constructor called
	 * Then the object should constructed with default record size
	 */
	@Test
	public void whenInvalidRecordSizeProvidedThenDefaultSizeShouldReturn() {
		EmployeePayslip payslip =new EmployeePayslip(TEST_INPUT_FILE, TEST_OUTPUT_FILE, TEST_INPUT_FILE);
		assertTrue(EmployeePayslip.DEFAULT_RECORD_SIZE.equalsIgnoreCase(String.valueOf(payslip.getRecordSize())));
	}
	
	/**
	 * Given all parameters provided 
	 * When the constructor called
	 * Then the object should constructed with pass in record size
	 */
	@Test
	public void whenValidRecordSizeProvidedThenPassInSizeShouldReturn() {
		EmployeePayslip payslip =new EmployeePayslip(TEST_INPUT_FILE, TEST_OUTPUT_FILE, TEST_RECORD_SIZE2);
		assertTrue(TEST_RECORD_SIZE2.equalsIgnoreCase(String.valueOf(payslip.getRecordSize())));
	}
}
