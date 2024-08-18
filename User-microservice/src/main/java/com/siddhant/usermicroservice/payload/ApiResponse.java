package com.siddhant.usermicroservice.payload;

import lombok.Data;

@Data
public class ApiResponse {

	private String message;
	private boolean success;
    private String statusCode;
	public ApiResponse() {
		super();
	}
	public ApiResponse(String message, boolean success, String statusCode) {
		super();
		this.message = message;
		this.success = success;
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
    
}
