package com.dbs.web.beans;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Customeruser {
	
	@Id
	private int userid;
	private String username;
	@OneToOne
	@JoinColumn(name="customerid")
	private Customer customer;
	private String userpassword;
	public Customeruser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customeruser(int userId, String userName, Customer customer, String userPassword) {
		super();
		this.userid = userId;
		this.username = userName;
		this.customer = customer;
		this.userpassword = userPassword;
	}
	public int getUserId() {
		return userid;
	}
	public void setUserId(int userId) {
		this.userid = userId;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getUserPassword() {
		return userpassword;
	}
	public void setUserPassword(String userPassword) {
		this.userpassword = userPassword;
	}
	
}
