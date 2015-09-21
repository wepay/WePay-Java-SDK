package com.wepay.model.data;

public class WithdrawalData {
	public Long createTime;
	public Long captureTime;
	public String callbackUri;
	public String redirectUri;
	public String withdrawalUri;
	
	public Long getCreateTime() {
		return createTime;
	}
	public Long getCaptureTime() {
		return captureTime;
	}
	public String getCallbackUri() {
		return callbackUri;
	}
	public String getRedirectUri() {
		return redirectUri;
	}
	public String getWithdrawalUri() {
		return withdrawalUri;
	}
}
