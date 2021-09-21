package com.dbs.web.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transfertypes {
	@Id
	private char transfertypecode;
	private String transfertypedescription;
	public Transfertypes() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transfertypes(char transferTypeCode, String transferTypeDescription) {
		super();
		this.transfertypecode = transferTypeCode;
		this.transfertypedescription = transferTypeDescription;
	}
	public char getTransferTypeCode() {
		return transfertypecode;
	}
	public void setTransferTypeCode(char transferTypeCode) {
		this.transfertypecode = transferTypeCode;
	}
	public String getTransferTypeDescription() {
		return transfertypedescription;
	}
	public void setTransferTypeDescription(String transferTypeDescription) {
		this.transfertypedescription = transferTypeDescription;
	}


}
