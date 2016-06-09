package au.com.myob.payslip.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import au.com.myob.payslip.comm.Constants.Numeral;
import au.com.myob.payslip.model.EmployeeSalaryRecord;

/**
 * Input reader for input file.
 */
public class InputReader {

	private static final int FOUR = 4;
	private String fileName;
	private int recordSize;
	
	public InputReader(final String fileName, final int recordSize) {
		this.fileName = fileName;
		this.recordSize = recordSize;
	}

	public List<EmployeeSalaryRecord> read() throws IOException {
		Path path = Paths.get(this.fileName);
		return Files.lines(path).limit(recordSize)
				.map(string -> string.split(",", 5))
				.map(array -> new EmployeeSalaryRecord(array[Numeral.ZERO], 
						array[Numeral.ONE], array[Numeral.TWO], 
						array[Numeral.THREE], array[FOUR]))
				.collect(Collectors.toList());
	}

}
