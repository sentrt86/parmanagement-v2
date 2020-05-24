package com.htc.par.model;

import java.util.Date;

// Class for Exception details

public class ResponseException {
	

	
	private Date timestamp;
    private String status;
	private String message;
	private String details;
	
	
	public ResponseException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseException(Date timestamp, String status,String message, String details) {
		super();
		this.timestamp = timestamp;
		this.status = status;
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
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ResponseException [timestamp=" + timestamp + ", status=" + status + ", message=" + message
				+ ", details=" + details + "]";
	}


}
