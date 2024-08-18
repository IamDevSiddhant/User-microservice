package com.siddhant.usermicroservice.exception;

public class GlobalException extends RuntimeException {

	public GlobalException() {
		super("Not found on Server");
	}
		
	public GlobalException(String s) {
		super(s);
	}
		
	
	
}
