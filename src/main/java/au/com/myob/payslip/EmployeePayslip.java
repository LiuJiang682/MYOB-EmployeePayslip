package au.com.myob.payslip;

public class EmployeePayslip {

	
	private static final int TWO = 2;
	private static final int ONE = 1;
	private static final String HELP = "h";
	private static final int ZERO = 0;
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
		
		
	}
	
	public static void main(String[] args) {
		String inputFileName = DEFAULT_INPUT_FILE;
		String outputFileName = DEFAULT_OUTPUT_FILE;
		String recordSize = DEFAULT_RECORD_SIZE;
		
		if (null != args) {
			if (ONE == args.length) {
				String firstParam = args[ZERO];
				if (HELP.equalsIgnoreCase(firstParam)) {
					usage();
					return;
				}
				else {
					inputFileName = firstParam;
				}
			}
			else if (TWO == args.length) {
				inputFileName = args[ZERO];
				outputFileName = args[ONE];
			}
			else if (3 == args.length) {
				inputFileName = args[ZERO];
				outputFileName = args[ONE];
				recordSize = args[TWO];
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
