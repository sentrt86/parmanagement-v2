package com.htc.par.exceptions;

@SuppressWarnings("serial")
public class ResourceAccessException  extends RuntimeException{
	
	public ResourceAccessException() {
		super();
	}


	public ResourceAccessException(String message, Throwable cause, boolean
			enableSuppression, boolean writableStackTrace) { 
		super(message, cause,enableSuppression, writableStackTrace); 
	}

	public ResourceAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceAccessException(String message) { 
		super(message); 
	}

	public ResourceAccessException(Throwable cause) { 
		super(cause);
	}

}
