package com.wepay.exception;

import com.wepay.net.WePayResource;

public class WePayException extends Exception {
		
	private String error;
	private String error_description;
	private Integer error_code;
	
	public WePayException(String message) {
		super(message);
	}
	
	public WePayException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public String getError() {
		return error;
	}
	
	public String getErrorDescription() {
		return error_description;
	}
	
	public Integer getErrorCode() {
		return error_code;
	}
	
	public String getMessage() {
		return WePayResource.gson.toJson(this);
	}

}