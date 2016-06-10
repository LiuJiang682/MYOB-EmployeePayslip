package au.com.myob.payslip.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class EmployeeMonthlyPayRecord implements Serializable {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = -3006817405466575335L;

	private static final Object LINE_SEPARATOR = System.getProperty("line.separator");
	private static final String DELIM = ",";

	private String name;
	private String payPeriod;
	private String grossIncome;
	private String incomeTax;
	private String netIncome;
	private String superContribution;

	public EmployeeMonthlyPayRecord(final String name, 
			final String payPeriod, final String grossIncome,
			final String incomeTax, final String netIncome, 
			final String superContribution) {
		this.name = name;
		this.payPeriod = payPeriod;
		this.grossIncome = grossIncome;
		this.incomeTax = incomeTax;
		this.netIncome = netIncome;
		this.superContribution = superContribution;
	}

	public String getName() {
		return name;
	}

	public String getPayPeriod() {
		return payPeriod;
	}

	public String getGrossIncome() {
		return grossIncome;
	}

	public String getIncomeTax() {
		return incomeTax;
	}

	public String getNetIncome() {
		return netIncome;
	}

	public String getSuperContribution() {
		return superContribution;
	}
	
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	public String toString() {
		StringBuilder buf = new StringBuilder(name);
		buf.append(DELIM);
		buf.append(payPeriod);
		buf.append(DELIM);
		buf.append(grossIncome);
		buf.append(DELIM);
		buf.append(incomeTax);
		buf.append(DELIM);
		buf.append(netIncome);
		buf.append(DELIM);
		buf.append(superContribution);
		buf.append(LINE_SEPARATOR);
		return buf.toString();
	}
}
