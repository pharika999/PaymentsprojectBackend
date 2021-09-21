package com.dbs.web.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Logger {
	@Id
	private int loggerid;
	@OneToOne
	@JoinColumn(name="customerid")
	private Customer customer;
	@OneToOne
	@JoinColumn(name="userid")
	private Customeruser user;
	@OneToOne
	@JoinColumn(name="employeeid")
	private Employee employee;
	private String screename;
	private String action;
	private String ipaddress;
	public Logger() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Logger(int loggerId, Customer customer, Customeruser user, Employee employee, String screename, String action,
			String ipAddress) {
		super();
		this.loggerid = loggerId;
		this.customer = customer;
		this.user = user;
		this.employee = employee;
		this.screename = screename;
		this.action = action;
		this.ipaddress = ipAddress;
	}
	public int getLoggerId() {
		return loggerid;
	}
	public void setLoggerId(int loggerId) {
		this.loggerid = loggerId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Customeruser getUser() {
		return user;
	}
	public void setUser(Customeruser user) {
		this.user = user;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employeeId) {
		this.employee = employeeId;
	}
	public String getScreename() {
		return screename;
	}
	public void setScreename(String screename) {
		this.screename = screename;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getIpAddress() {
		return ipaddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipaddress = ipAddress;
	}
}
