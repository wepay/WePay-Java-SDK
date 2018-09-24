package com.wepay.exception;

import com.wepay.net.WePayResource;
import java.util.List;

public class WePayException extends Exception {

	private class ErrorDetail {
		private List<String> target;
		private String targetType;
		private String reasonCode;
		private String message;

		public List<String> getTarget() {
		    return target;
		}

		public String getTargetType() {
		    return targetType;
		}

		public String getReasonCode() {
		    return reasonCode;
		}

		public String getMessage() {
		    return message;
		}

	}

	private String error;
	private String errorDescription;
	private Integer errorCode;
	private List<ErrorDetail> details;
	private String documentationUrl;

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

	public List<ErrorDetail> getDetails() {
	    return details;
	}

	public String getDocumentationUrl() {
		return documentationUrl;
	}

	public String getMessage() {
		return WePayResource.gson.toJson(this);
	}

}