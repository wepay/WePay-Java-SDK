
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
		call1.parameters = {"name":"Adam", "description":"first account created from batch"}
		
		BatchData call2 = new BatchData();
		call2.callClass = "Account";
		call2.callFunction = "create";
		call2.authorization = myAccessToken;
		call2.parameters = {"name":"Bob", "description":"second account created from batch"}
		
		Batch[] responses = Batch.create({call1, call2}, myAccessToken);
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
