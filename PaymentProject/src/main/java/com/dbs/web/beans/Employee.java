package com.dbs.web.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
	@Id
	private int employeeid;
	private String employeename;
	private String employeepassword;
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(int employeeId, String employeeName, String employeePassword) {
		super();
		this.employeeid = employeeId;
		this.employeename = employeeName;
		this.employeepassword = employeePassword;
	}
	public int getEmployeeId() {
		return employeeid;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeid = employeeId;
	}
	public String getEmployeeName() {
		return employeename;
	}
	public void setEmployeeName(String employeeName) {
		this.employeename = employeeName;
	}
	public String getEmployeePassword() {
		return employeepassword;
	}
	public void setEmployeePassword(String employeePassword) {
		this.employeepassword = employeePassword;
	}

}
