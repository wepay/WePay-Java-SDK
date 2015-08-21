package com.wepay.model.data;
import org.json.*;

public class EmailMessageData {
	public String toPayer;
	public String toPayee;

	public static JSONObject build_email_message(EmailMessageData info) throws JSONException {
		JSONObject o = new JSONObject();
		if (info.toPayer != null) o.put("to_payer", info.toPayer);
		if (info.toPayee != null) o.put("to_payee", info.toPayee);
		return o;
	}
}
