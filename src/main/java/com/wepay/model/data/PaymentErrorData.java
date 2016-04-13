package com.wepay.model.data;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentErrorData {

    public String type;
    public BankAccountPaymentErrorData bankAccount;

    // This object may be present in the response, but should never be passed in as part of the request
}
