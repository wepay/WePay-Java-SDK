
public class BatchCalls {
	
	/**
	 * The calls in the main function below are not intended to be executed as they are. 
	 * Think of this code as a template for how you might structure API calls within your 
	 * own code. Below each call is an example of the result of the call (either the call's
	 * response or how the call affect's the objects it is called on. 
	 */
	
	public static void main() {
		
		/****************************************************************************/
		/**
		 * CREATE call
		 */
		BatchData call1 = new BatchData();
		call1.callClass = "Account";
		call1.callFunction = "create";
		call1.authorization = myAccessToken;
		JSONObject params1 = new JSONObject();
		params1.put("name", "Adam");
		params1.put("description", "first account created from batch");
		call1.parameters = params1;

		BatchData call2 = new BatchData();
		call2.callClass = "Account";
		call2.callFunction = "create";
		call2.authorization = myAccessToken;
		JSONObject params2 = new JSONObject();
		params2.put("name", "Bob");
		params2.put("description", "second account created from batch");
		call2.parameters = params2;

		BatchData[] calls = {call1, call2};

		Batch[] responses = Batch.create(calls, myAccessToken);

		// Print responses
		for (int i=0; i < responses.length; i++) {
			System.out.println(responses[i].getCall());
			System.out.println(responses[i].getResponse());
		}
		/**
		   responses:
			{
	          "call": "/account/create",
	          "reference_id": null,
	          "response": {
	            "account_id": 1938233429,
	            "account_uri": "https://stage.wepay.com/account/1938233429"
	          }
	        },
	        {
	          "call": "/account/create",
	          "reference_id": null,
	          "response": {
	            "account_id": 478040119,
	            "account_uri": "https://stage.wepay.com/account/478040119"
	          }
	        }		 
        */
		/****************************************************************************/

	}

}
