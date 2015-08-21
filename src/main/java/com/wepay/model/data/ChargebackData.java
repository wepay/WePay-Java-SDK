package com.wepay.model.data;
import java.math.BigDecimal;
import org.json.*;

public class ChargebackData {
	public BigDecimal amountChargedBack;
	public String disputeUri;

	public static JSONObject build_chargeback(ChargebackData info) throws JSONException {
		JSONObject o = new JSONObject();
		o.put("amount_charged_back", info.amountChargedBack);
		o.put("dispute_uri", info.disputeUri);
		return o;
	}
}
