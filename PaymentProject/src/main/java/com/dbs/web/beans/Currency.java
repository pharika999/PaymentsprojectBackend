package com.dbs.web.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Currency {
	
	@Id
	private String currencycode;
	private String currencyname;
	private double conversionrate;
	
	public Currency() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Currency(String currencyCode, String currencyName, double conversionRate) {
		super();
		this.currencycode = currencyCode;
		this.currencyname = currencyName;
		this.conversionrate = conversionRate;
	}
	public String getCurrencyCode() {
		return currencycode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencycode = currencyCode;
	}
	public String getCurrencyName() {
		return currencyname;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyname = currencyName;
	}
	public double getConversionRate() {
		return conversionrate;
	}
	public void setConversionRate(double conversionRate) {
		this.conversionrate = conversionRate;
	}
	

}
