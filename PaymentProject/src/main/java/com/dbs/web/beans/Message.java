package com.dbs.web.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Message {
	@Id
	private String messagecode;
	private String instruction;
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Message(String messageCode, String instruction) {
		super();
		this.messagecode = messageCode;
		this.instruction = instruction;
	}
	public String getMessageCode() {
		return messagecode;
	}
	public void setMessageCode(String messageCode) {
		this.messagecode = messageCode;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
}
