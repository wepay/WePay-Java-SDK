package com.wepay.model;

import com.wepay.exception.WePayException;
import com.wepay.model.data.*;
import com.wepay.net.WePayResource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;

public class Checkout extends WePayResource {

  protected Long checkoutId;
  protected String state;
  protected String softDescriptor;
  protected BigDecimal gross;
  protected ChargebackData chargeback;
  protected CheckoutRefundData refund;
  protected PayerData payer;
  protected Long createTime;
  protected Long[] payerRbitIds;
  protected Long[] transactionRbitIds;
  protected Boolean inReview;

  protected CheckoutData checkoutData;

  public Checkout(Long checkoutId) {
    this.checkoutId = checkoutId;
  }

  public static Checkout fetch(Long checkoutId, String accessToken) throws JSONException, IOException, WePayException {
    HeaderData headerData = new HeaderData();
    headerData.accessToken = accessToken;
    return Checkout.fetch(checkoutId, headerData);
  }

  public static Checkout fetch(Long checkoutId, HeaderData headerData) throws JSONException, IOException, WePayException {
    JSONObject params = new JSONObject();
    params.put("checkout_id", checkoutId);
    String response = request("/checkout", params, headerData);
    Checkout c = gson.fromJson(response, Checkout.class);
    CheckoutData cd = gson.fromJson(response, CheckoutData.class);
    c.checkoutData = cd;
    return c;
  }

  public static Checkout[] find(CheckoutFindData findData, String accessToken) throws JSONException, IOException, WePayException {
    HeaderData headerData = new HeaderData();
    headerData.accessToken = accessToken;
    return Checkout.find(findData, headerData);
  }

  public static Checkout[] find(CheckoutFindData findData, HeaderData headerData) throws JSONException, IOException, WePayException {
    JSONObject params = new JSONObject();
    if (findData.accountId != null) {
      params.put("account_id", findData.accountId);
    }
    if (findData.start != null) {
      params.put("start", findData.start);
    }
    if (findData.limit != null) {
      params.put("limit", findData.limit);
    }
    if (findData.referenceId != null) {
      params.put("reference_id", findData.referenceId);
    }
    if (findData.state != null) {
      params.put("state", findData.state);
    }
    if (findData.preapprovalId != null) {
      params.put("preapproval_id", findData.preapprovalId);
    }
    if (findData.startTime != null) {
      params.put("start_time", findData.startTime);
    }
    if (findData.endTime != null) {
      params.put("end_time", findData.endTime);
    }
    if (findData.sortOrder != null) {
      params.put("sort_order", findData.sortOrder);
    }
    if (findData.shippingFee != null) {
      params.put("shipping_fee", findData.shippingFee);
    }
    JSONArray results = new JSONArray(request("/checkout/find", params, headerData));
    Checkout[] found = new Checkout[results.length()];
    for (int i = 0; i < found.length; i++) {
      Checkout c = gson.fromJson(results.get(i).toString(), Checkout.class);
      CheckoutData cd = gson.fromJson(results.get(i).toString(), CheckoutData.class);
      c.checkoutData = cd;
      found[i] = c;
    }
    return found;
  }

  public static Checkout create(CheckoutData data, String accessToken) throws JSONException, IOException, WePayException {
    HeaderData headerData = new HeaderData();
    headerData.accessToken = accessToken;
    return Checkout.create(data, headerData);
  }

  public static Checkout create(CheckoutData data, HeaderData headerData) throws JSONException, IOException, WePayException {
    JSONObject params = new JSONObject();
    params.put("account_id", data.accountId);
    params.put("short_description", data.shortDescription);
    params.put("type", data.type);
    params.put("amount", data.amount);
    params.put("currency", data.currency);

    if (data.longDescription != null) {
      params.put("long_description", data.longDescription);
    }

    if (data.emailMessage != null) {
      params.put("email_message", EmailMessageData.build_email_message(data.emailMessage));
    }
    if (data.fee != null) {
      params.put("fee", FeeData.build_fee(data.fee));
    }

    if (data.callbackUri != null) {
      params.put("callback_uri", data.callbackUri);
    }
    if (data.autoRelease != null) {
      params.put("auto_release", data.autoRelease);
    }
    if (data.referenceId != null) {
      params.put("reference_id", data.referenceId);
    }
    if (data.uniqueId != null) {
      params.put("unique_id", data.uniqueId);
    }

    if (data.hostedCheckout != null) {
      params.put("hosted_checkout", HostedCheckoutData.build_hosted_checkout(data.hostedCheckout));
    }

    if (data.paymentMethod != null) {
      params.put("payment_method", data.paymentMethod.toJSON());
    }

    if (data.deliveryType != null) {
      params.put("delivery_type", data.deliveryType);
    }

    if (data.payerRbits != null) {
      String payerRbitsJson = gson.toJson(data.payerRbits);
      params.put("payer_rbits", new JSONArray(payerRbitsJson));
    }

    if (data.transactionRbits != null) {
      String transactionRbitsJson = gson.toJson(data.transactionRbits);
      params.put("transaction_rbits", new JSONArray(transactionRbitsJson));
    }

    if (data.initiatedBy != null) {
      params.put("initiated_by", data.initiatedBy);
    }

    if (data.transactionType != null) {
      params.put("transaction_type", data.transactionType);
    }

    if (data.strongCustomerAuthentication != null) {
      params.put("strong_customer_authentication", data.strongCustomerAuthentication.toJSON());
    }

    String response = request("/checkout/create", params, headerData);
    Checkout c = gson.fromJson(response, Checkout.class);
    CheckoutData cd = gson.fromJson(response, CheckoutData.class);

    if (data.emailMessage != null) {
      cd.emailMessage = data.emailMessage;
    }
    if (data.uniqueId != null) {
      cd.uniqueId = data.uniqueId;
    }
    if (data.hostedCheckout != null && data.hostedCheckout.fundingSources != null) {
      cd.hostedCheckout.fundingSources = data.hostedCheckout.fundingSources;
    }
    c.checkoutData = cd;

    return c;
  }

  public void modify(String newCallbackUri, String accessToken) throws JSONException, IOException, WePayException {
    //overload function for single parameter modification: callbackUri
    CheckoutData data = new CheckoutData();
    data.callbackUri = newCallbackUri;
    this.modify(data, accessToken);
  }

  public void modify(CheckoutData data, String accessToken) throws JSONException, IOException, WePayException {
    HeaderData headerData = new HeaderData();
    headerData.accessToken = accessToken;
    this.modify(data, headerData);
  }

  public void modify(CheckoutData data, HeaderData headerData) throws JSONException, IOException, WePayException {
    JSONObject params = new JSONObject();
    params.put("checkout_id", this.checkoutId);
    params.put("callback_uri", data.callbackUri);

    if (data.payerRbits != null) {
      String payerRbitsJson = gson.toJson(data.payerRbits);
      params.put("payer_rbits", new JSONArray(payerRbitsJson));
    }
    if (data.transactionRbits != null) {
      String transactionRbitsJson = gson.toJson(data.transactionRbits);
      params.put("transaction_rbits", new JSONArray(transactionRbitsJson));
    }

    String response = request("/checkout/modify", params, headerData);

    Checkout c = gson.fromJson(response, Checkout.class);
    CheckoutData cd = gson.fromJson(response, CheckoutData.class);
    cd.callbackUri = data.callbackUri;

    this.checkoutId = c.checkoutId;
    this.state = c.state;
    this.softDescriptor = c.softDescriptor;
    this.gross = c.gross;
    this.chargeback = c.chargeback;
    this.refund = c.refund;
    this.payer = c.payer;
    this.checkoutData = cd;
    this.payerRbitIds = c.payerRbitIds;
    this.transactionRbitIds = c.transactionRbitIds;
  }

  public void cancel(String cancelReason, String accessToken) throws JSONException, IOException, WePayException {
    HeaderData headerData = new HeaderData();
    headerData.accessToken = accessToken;
    this.cancel(cancelReason, headerData);
  }

  public void cancel(String cancelReason, HeaderData headerData) throws JSONException, IOException, WePayException {
    JSONObject params = new JSONObject();
    params.put("checkout_id", this.checkoutId);
    params.put("cancel_reason", cancelReason);
    request("/checkout/cancel", params, headerData);
    if (this.checkoutData != null) {
      this.checkoutData.cancelReason = cancelReason;
    }
  }

  public void refund(CheckoutRefundData refundData, String accessToken) throws JSONException, IOException, WePayException {
    HeaderData headerData = new HeaderData();
    headerData.accessToken = accessToken;
    this.refund(refundData, headerData);
  }

  public void refund(CheckoutRefundData refundData, HeaderData headerData) throws JSONException, IOException, WePayException {
    JSONObject params = new JSONObject();
    params.put("checkout_id", this.checkoutId);
    params.put("refund_reason", refundData.refundReason);
    if (refundData.amount != null) {
      params.put("amount", refundData.amount);
    }
    if (refundData.appFee != null) {
      params.put("app_fee", refundData.appFee);
    }
    if (refundData.payerEmailMessage != null) {
      params.put("payer_email_message", refundData.payerEmailMessage);
    }
    if (refundData.payeeEmailMessage != null) {
      params.put("payee_email_message", refundData.payeeEmailMessage);
    }
    request("/checkout/refund", params, headerData);
    if (this.checkoutData != null) {
      this.checkoutData.refundReason = refundData.refundReason;
    }
  }


  public void manualCapture(String accessToken) throws JSONException, IOException, WePayException {
    HeaderData headerData = new HeaderData();
    headerData.accessToken = accessToken;
    this.manualCapture(headerData);
  }

  public void manualCapture(HeaderData headerData) throws JSONException, IOException, WePayException {
    JSONObject params = new JSONObject();
    params.put("checkout_id", this.checkoutId);
    Checkout c = gson.fromJson((request("/checkout/capture", params, headerData)), Checkout.class);
    this.state = c.state;
  }

	/*
   * checkout/capture was renamed to checkout/release in the 2016-07-13 release
	 */

  public void release(String accessToken) throws JSONException, IOException, WePayException {
    HeaderData headerData = new HeaderData();
    headerData.accessToken = accessToken;
    this.release(headerData);
  }

  public void release(HeaderData headerData) throws JSONException, IOException, WePayException {
    JSONObject params = new JSONObject();
    params.put("checkout_id", this.checkoutId);
    Checkout c = gson.fromJson((request("/checkout/release", params, headerData)), Checkout.class);
    this.state = c.state;
  }

  public Long getCheckoutId() {
    return checkoutId;
  }

  public Long getAccountId() {
    return checkoutData.accountId;
  }

  public String getPaymentMethodType() {
    return checkoutData.paymentMethod.type;
  }

  public Long getPreapprovalId() {
    return checkoutData.paymentMethod.preapproval.id;
  }

  public Long getCreditCardId() {
    return checkoutData.paymentMethod.creditCard.id;
  }

  public CreditCardAdditionalData getCreditCardAdditionalData() {
    CreditCardAdditionalData additionalData = null;
    if (this.checkoutData != null && this.checkoutData.paymentMethod != null &&
        this.checkoutData.paymentMethod.creditCard != null) {
      additionalData = this.checkoutData.paymentMethod.creditCard.data;
    }
    return additionalData;
  }

  public String getState() {
    return state;
  }

  public Long getCreateTime() {
    return createTime;
  }

  public String getSoftDescriptor() {
    return softDescriptor;
  }

  public String getShortDescription() {
    return checkoutData.shortDescription;
  }

  public String getLongDescription() {
    return checkoutData.longDescription;
  }

  public String getCurrency() {
    return checkoutData.currency;
  }

  public BigDecimal getAmount() {
    return checkoutData.amount;
  }

  public BigDecimal getProcessingFee() {
    return checkoutData.fee.processingFee;
  }

  public BigDecimal getGross() {
    return gross;
  }

  public BigDecimal getAppFee() {
    return checkoutData.fee.appFee;
  }

  public String getFeePayer() {
    return checkoutData.fee.feePayer;
  }

  public String getReferenceId() {
    return checkoutData.referenceId;
  }

  public String getRedirectUri() {
    return checkoutData.hostedCheckout.redirectUri;
  }

  public String getCallbackUri() {
    return checkoutData.callbackUri;
  }

  public String getDisputeUri() {
    return chargeback.disputeUri;
  }

  public BigDecimal getAmountChargedback() {
    return chargeback.amountChargedBack;
  }

  public String getPayerEmail() {
    return payer.email;
  }

  public String getPayerName() {
    return payer.name;
  }

  public String getPayerHomeAddress() {
    return payer.homeAddress;
  }

  public String getDeliveryType() {
    return checkoutData.deliveryType;
  }

  public Boolean isAutoRelease() {
    return checkoutData.autoRelease;
  }

  public Boolean isRequireShipping() {
    return checkoutData.hostedCheckout.requireShipping;
  }

  public AddressData getShippingAddress() {
    return checkoutData.hostedCheckout.shippingAddress;
  }

  public BigDecimal getAmountRefunded() {
    return refund.amountRefunded;
  }

  public String getRefundReason() {
    return refund.refundReason;
  }

  public String getCheckoutUri() {
    return checkoutData.hostedCheckout.checkoutUri;
  }

  public String getType() {
    return checkoutData.type;
  }

  public String getFallbackUri() {
    return checkoutData.hostedCheckout.fallbackUri;
  }

  public BigDecimal getShippingFee() {
    return checkoutData.hostedCheckout.shippingFee;
  }

  public String[] getFundingSources() {
    return checkoutData.hostedCheckout.fundingSources;
  }

  public Long[] getPayerRbitIds() {
    return payerRbitIds;
  }

  public Long[] getTransactionRbitIds() {
    return transactionRbitIds;
  }

  public Boolean isInReview() {
    return inReview;
  }

  public PaymentErrorData getPaymentErrorData() {
    return checkoutData.paymentError;
  }

  public PaymentMethodData getPaymentMethodData() {
    return checkoutData.paymentMethod;
  }

  public NPOInformationData getNPOInformation() {
    return checkoutData.npoInformation;
  }

  public CheckoutData getAllCheckoutData() {
    return checkoutData;
  }


}
