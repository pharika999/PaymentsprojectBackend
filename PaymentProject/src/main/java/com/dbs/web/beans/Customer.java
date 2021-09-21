package com.dbs.web.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {
	@Id
	private String customerid;
	private String accountholdername;
	private boolean overdraftflag;
	private double clearbalance;
	private String customeraddress;
	private String customercity;
	private char customertype;
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(String customerid, String accountholdername, boolean overdraftflag, double clearbalance,
			String customeraddress, String customercity, char customertype) {
		super();
		this.customerid = customerid;
		this.accountholdername = accountholdername;
		this.overdraftflag = overdraftflag;
		this.clearbalance = clearbalance;
		this.customeraddress = customeraddress;
		this.customercity = customercity;
		this.customertype = customertype;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getAccountholdername() {
		return accountholdername;
	}
	public void setAccountholdername(String accountholdername) {
		this.accountholdername = accountholdername;
	}
	public boolean isOverdraftflag() {
		return overdraftflag;
	}
	public void setOverdraftflag(boolean overdraftflag) {
		this.overdraftflag = overdraftflag;
	}
	public double getClearbalance() {
		return clearbalance;
	}
	public void setClearbalance(double clearbalance) {
		this.clearbalance = clearbalance;
	}
	public String getCustomeraddress() {
		return customeraddress;
	}
	public void setCustomeraddress(String customeraddress) {
		this.customeraddress = customeraddress;
	}
	public String getCustomercity() {
		return customercity;
	}
	public void setCustomercity(String customercity) {
		this.customercity = customercity;
	}
	public char getCustomertype() {
		return customertype;
	}
	public void setCustomertype(char customertype) {
		this.customertype = customertype;
	}
}
