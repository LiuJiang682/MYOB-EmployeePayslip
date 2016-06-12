package au.com.myob.payslip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import au.com.myob.payslip.calcuation.MonthlyPayCalculator;
import au.com.myob.payslip.comm.Constants.Numeral;
import au.com.myob.payslip.io.InputReader;
import au.com.myob.payslip.io.OutputWriter;
import au.com.myob.payslip.model.EmployeeMonthlyPayRecord;
import au.com.myob.payslip.model.EmployeeSalaryRecord;

/**
 * The main class for MYOB code exercise of employee monthly pay slip.
 * For the detail requirements of this program, please read the README.md.
 */
public class EmployeePayslip {

	private static final String HELP = "h";

	public static final String DEFAULT_RECORD_SIZE = "6000";
	public static final String DEFAULT_OUTPUT_FILE = "src/test/resources/outputFile.csv";
	public static final String DEFAULT_INPUT_FILE = "src/test/resources/inputFile.csv";
	public static final String USAGE = "Usage: java -jar EmployeePayslip-1.0.0-jar-with-dependencies.jar";
	
	private String inputFileName;
	private String outputFileName;
	private String recordSizeString;
	private int recordSize;
	
	/**
	 * Constructor
	 * 
	 * @param inputFileName the input file name
	 * @param outputFileName the output file name
	 * @param recordSize the max record size.
	 */
	public EmployeePayslip(final String inputFileName, final String outputFileName, final String recordSize) {
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		this.recordSizeString = recordSize;
		try {
			this.recordSize = Integer.parseInt(recordSizeString);
		}
		catch (NumberFormatException e) {
			this.recordSize = Integer.parseInt(DEFAULT_RECORD_SIZE);
		}
	}
	
	/**
	 * The method do all work. It will:
	 * 
	 * 1. Read the records from input file.
	 * 2. Perform monthly pay calculation.
	 * 3. Write the monthly pay record to output file.
	 */
	protected void run() {
		try {
			List<EmployeeSalaryRecord> inputs = getInputContents();
			List<EmployeeMonthlyPayRecord> records = doPayCalculation(inputs);
			writeToFile(records);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method writes to the output file with provided pay records.
	 * @param records the pay records.
	 * @throws IOException raise when write operation failed.
	 */
	protected void writeToFile(List<EmployeeMonthlyPayRecord> records) throws IOException {
		if (CollectionUtils.isNotEmpty(records)) {
			OutputWriter writer = new OutputWriter(this.outputFileName);
			writer.write(records);
		}
	}

	/**
	 * This method performs the monthly pay calcuation from the input records.
	 * @param inputs the input records from input file.
	 * @return list of pay records.
	 */
	protected List<EmployeeMonthlyPayRecord> doPayCalculation(List<EmployeeSalaryRecord> inputs) {
		List<EmployeeMonthlyPayRecord> payRecords = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(inputs)) {
			for (EmployeeSalaryRecord input:  inputs) {
				payRecords.add(MonthlyPayCalculator.doCalculation(input));
			}
		}
		
		return payRecords;
	}

	/**
	 * This method reads the employee salary records from input file.
	 * @return list of salary records.
	 * @throws IOException raise when read operation failed.
	 */
	protected List<EmployeeSalaryRecord> getInputContents() throws IOException {
		InputReader inputReader = new InputReader(this.inputFileName, this.recordSize);
		return inputReader.read();
	}

	/**
	 * The entry point for this program.
	 * 
	 * @param args parameters user input. It can be as following:
	 * 			java -jar EmployeePayslip-1.0.0-jar-with-dependencies.jar
	 * or
	 * 			java -jar EmployeePayslip-1.0.0-jar-with-dependencies.jar inputFile
	 * or
	 * 			java -jar EmployeePayslip-1.0.0-jar-with-dependencies.jar inputFile outputFile
	 * or
	 * 			java -jar EmployeePayslip-1.0.0-jar-with-dependencies.jar inputFile outputFile 8000
	 * 
	 */
	public static void main(String[] args) {
		String inputFileName = DEFAULT_INPUT_FILE;
		String outputFileName = DEFAULT_OUTPUT_FILE;
		String recordSize = DEFAULT_RECORD_SIZE;
		
		if (null != args) {
			if (Numeral.ONE == args.length) {
				String firstParam = args[Numeral.ZERO];
				if (HELP.equalsIgnoreCase(firstParam)) {
					usage();
					return;
				}
				else {
					inputFileName = firstParam;
				}
			}
			else if (Numeral.TWO == args.length) {
				inputFileName = args[Numeral.ZERO];
				outputFileName = args[Numeral.ONE];
			}
			else if (Numeral.THREE == args.length) {
				inputFileName = args[Numeral.ZERO];
				outputFileName = args[Numeral.ONE];
				recordSize = args[Numeral.TWO];
			}
		}

		new EmployeePayslip(inputFileName,
				outputFileName,
				recordSize)
			.run();;
	}

	private static void usage() {
		System.out.println(USAGE);
	}

	public int getRecordSize() {
		return this.recordSize;
	}

}
