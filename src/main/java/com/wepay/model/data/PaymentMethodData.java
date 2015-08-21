package com.wepay.model.data;
import org.json.*;
import com.wepay.model.data.*;

public class PaymentMethodData {
	public String type;
	public PMCreditCardData creditCard;
	public PMPreapprovalData preapproval;

	public static JSONObject build_payment_method(PaymentMethodData info) throws JSONException {
		JSONObject o = new JSONObject();
		o.put("type", info.type);
		if (info.creditCard != null) o.put("credit_card", PMCreditCardData.buildCreditCard(info.creditCard));
		if (info.preapproval != null) o.put("preapproval",  PMPreapprovalData.buildPreapproval(info.preapproval));
		return o;
	}
}
