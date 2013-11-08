package com.wepay.exception;

import com.wepay.net.WePayResource;

public class WePayException extends Exception {
		
	private String error;
	private String errorDescription;
	private Integer errorCode;
	
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
		return errorDescription;
	}
	
	public Integer getErrorCode() {
		return errorCode;
	}
	
	public String getMessage() {
		return WePayResource.gson.toJson(this);
	}

}