package au.com.myob.payslip.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class EmployeeSalaryRecord implements Serializable {

	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = -4670849409336843148L;

	private String firstName;
	private String lastName;
	private String annualSalary;
	private String superRate;
	private String payPeriod;
	
	public EmployeeSalaryRecord(String firstName, 
			String lastName, 
			String annualSalary, 
			String superRate, 
			String payPeriod) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.annualSalary = annualSalary;
		this.superRate = superRate;
		this.payPeriod = payPeriod;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAnnualSalary() {
		return annualSalary;
	}

	public String getSuperRate() {
		return superRate;
	}

	public String getPayPeriod() {
		return payPeriod;
	}
	
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
