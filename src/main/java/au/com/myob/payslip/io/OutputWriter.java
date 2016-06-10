package au.com.myob.payslip.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import au.com.myob.payslip.model.EmployeeMonthlyPayRecord;

public class OutputWriter {

	private String outputFileName;
	
	public OutputWriter(final String outputFile) {
		this.outputFileName = outputFile;
	}

	public void write(List<EmployeeMonthlyPayRecord> records) throws IOException {
		Path path = Paths.get(this.outputFileName);
		for (EmployeeMonthlyPayRecord record : records) {
			Files.write(path, record.toString().getBytes());
		}
	}

}
