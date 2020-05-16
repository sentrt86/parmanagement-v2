package com.htc.par.model;

import java.util.Date;

// Class for Exception details

public class ResponseException {
	

	
	private Date timestamp;
	private String message;
	private String details;
	public ResponseException(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	@Override
	public String toString() {
		return "ResponseException [timestamp=" + timestamp + ", message=" + message + ", details=" + details + "]";
	}
	

}
