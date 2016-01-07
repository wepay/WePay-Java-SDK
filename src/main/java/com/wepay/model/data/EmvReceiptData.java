package com.wepay.model.data;

import java.math.BigDecimal;

public class EmvReceiptData {
	public String applicationLabel;
	public String applicationIdentifier;
	public String terminalIdentification;
	public BigDecimal transactionAmount;
	public String transactionType;
	public String transactionCertificate;
	public String authorizationCode;
	public String merchantId;
	public String merchantName;
}
