package com.wepay.model.data;

import org.json.JSONException;
import org.json.JSONObject;

public class PMInlineCreditCardData {
    public String ccNumber;
    public Integer expirationMonth;
    public Integer expirationYear;
    public String userName;
    public AddressData address;
    public String cvv;
    public String email;
    public String referenceID;
    public Boolean autoUpdate;
    public String callbackUri;
    public String virtualTerminal;
    public Boolean paymentRequestFlag;
    public Boolean autoCapture;

    public JSONObject toJSON() throws JSONException{
        JSONObject o = new JSONObject();
        o.put("cc_number", ccNumber);
        o.put("expiration_month", expirationMonth);
        o.put("expiration_year", expirationYear);
        o.put("user_name", userName);
        o.put("address", AddressData.buildUnifiedAddress(address));
        o.put("cvv", cvv);
        o.put("email", email);
        o.put("reference_id", referenceID);
        o.put("auto_update", autoUpdate);
        o.put("callback_uri", callbackUri);
        o.put("virtual_terminal", virtualTerminal);
        o.put("payment_request_flag", paymentRequestFlag);
        o.put("auto_capture", autoCapture);

        return o;
    }
}
