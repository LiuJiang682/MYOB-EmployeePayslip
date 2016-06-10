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

public class EmployeePayslip {

	private static final String HELP = "h";

	public static final String DEFAULT_RECORD_SIZE = "6000";
	public static final String DEFAULT_OUTPUT_FILE = "src/test/resources/outputFile.csv";
	public static final String DEFAULT_INPUT_FILE = "src/test/resources/inputFile.csv";
	public static final String USAGE = "Usage: java au.com.myob.payslip.EmployeePayslip";
	
	private String inputFileName;
	private String outputFileName;
	private String recordSizeString;
	private int recordSize;
	
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
	
	protected void run() {
		try {
			List<EmployeeSalaryRecord> inputs = getInputContents();
			List<EmployeeMonthlyPayRecord> records = doPayCalculation(inputs);
			writeToFile(records);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	protected void writeToFile(List<EmployeeMonthlyPayRecord> records) throws IOException {
		if (CollectionUtils.isNotEmpty(records)) {
			OutputWriter writer = new OutputWriter(this.outputFileName);
			writer.write(records);
		}
	}

	protected List<EmployeeMonthlyPayRecord> doPayCalculation(List<EmployeeSalaryRecord> inputs) {
		List<EmployeeMonthlyPayRecord> payRecords = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(inputs)) {
			for (EmployeeSalaryRecord input:  inputs) {
				payRecords.add(MonthlyPayCalculator.doCalculation(input));
			}
		}
		
		return payRecords;
	}

	protected List<EmployeeSalaryRecord> getInputContents() throws IOException {
		InputReader inputReader = new InputReader(this.inputFileName, this.recordSize);
		return inputReader.read();
	}

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
