package com.wepay.model.data;
import org.json.*;
import com.wepay.model.data.*;

public class PaymentMethodData {
	public String type;
	public PMCreditCardData creditCard;
	public PMPreapprovalData preapproval;
	public PMPaymentBankData paymentBank;


	public JSONObject toJSON() throws JSONException {
		JSONObject o = new JSONObject();
		o.put("type", type);
		if (creditCard != null) o.put("credit_card", creditCard.toJSON()); // Only one of credit card, preapproval, or bank account can be present
		else if (preapproval != null) o.put("preapproval",  preapproval.toJSON());
		else if (paymentBank != null) o.put("payment_bank", paymentBank.toJSON());
		return o;
	}
}
