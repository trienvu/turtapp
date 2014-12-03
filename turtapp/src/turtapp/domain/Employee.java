package turtapp.domain;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long empId;
	private String empName;
	private Date dob;
	private Date doj;
	private String appraisalRemarks;
	private float salary;
	
	public long getEmpId() {
		return empId;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public String getAppraisalRemarks() {
		return appraisalRemarks;
	}
	public void setAppraisalRemarks(String appraisalRemarks) {
		this.appraisalRemarks = appraisalRemarks;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", dob="
				+ dob + ", doj=" + doj + ", appraisalRemarks="
				+ appraisalRemarks + ", salary=" + salary + ", getEmpId()="
				+ getEmpId() + ", getEmpName()=" + getEmpName() + ", getDob()="
				+ getDob() + ", getDoj()=" + getDoj()
				+ ", getAppraisalRemarks()=" + getAppraisalRemarks()
				+ ", getSalary()=" + getSalary() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
