package com.wepay.model.data;
import org.json.*;
import com.wepay.model.data.*;

public class PaymentMethodData {
	public String type;
	public PMCreditCardData creditCard;
	public PMPreapprovalData preapproval;
	public PMBankAccountData bankAccount;


	public JSONObject toJSON() throws JSONException {
		JSONObject o = new JSONObject();
		o.put("type", type);
		if (creditCard != null) o.put("credit_card", creditCard.toJSON()); // Only one of credit card, preapproval, or bank account can be present
		else if (preapproval != null) o.put("preapproval",  preapproval.toJSON());
		else if (bankAccount != null) o.put("bank_account", bankAccount.toJSON());
		return o;
	}
}
