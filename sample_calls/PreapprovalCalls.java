
public class PreapprovalCalls {
	
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
		PreapprovalData pData = new PreapprovalData();
		pData.period = "monthly";
		pData.shortDescription = "sample preapproval";
		Preapproval myPreapproval = Preapproval.create(pData, myAccessToken);
		/**
			myPreapproval: 
			{
			  "preapproval_id": 70569984,
			  "preapproval_uri": "https://stage.wepay.com/api/preapproval/70569984/60ff349a",
			  "preapproval_data": {
			    "short_description": "sample preapproval",
			    "period": "monthly"
			  }
			}
		 */
		/****************************************************************************/
		/**
		 * FETCH call
		 */
		Preapproval myPreapproval = Preapproval.fetch(70569984, myAccessToken);
		/**
			myPreapproval: 
			{
			  "preapproval_id": 70569984,
			  "preapproval_uri": "https://stage.wepay.com/api/preapproval/70569984/60ff349a",
			  "state": "new",
			  "manage_uri": "https://stage.wepay.com/preapproval/view/70569984/60ff349a",
			  "currency": "USD",
			  "create_time": 1375483850,
			  "preapproval_data": {
			    "account_id": 0,
			    "amount": 0,
			    "short_description": "sample preapproval",
			    "period": "monthly",
			    "app_fee": 0,
			    "fee_payer": "payer",
			    "frequency": 1,
			    "start_time": 1375483850,
			    "end_time": 1533268480,
			    "auto_recur": false
			  }
			}
		 */
		/****************************************************************************/
		/**
		 * FIND call
		 */
		Preapproval[] foundList = Preapproval.find(null, myAccessToken);
		/**
		    foundList: 
			[
			  {
			    "preapproval_id": 70569984,
			    "preapproval_uri": "https://stage.wepay.com/api/preapproval/70569984/60ff349a",
			    "state": "new",
			    "manage_uri": "https://stage.wepay.com/preapproval/view/70569984/60ff349a",
			    "currency": "USD",
			    "create_time": 1375483850,
			    "preapproval_data": {
			      "account_id": 0,
			      "amount": 0,
			      "short_description": "sample preapproval",
			      "period": "monthly",
			      "app_fee": 0,
			      "fee_payer": "payer",
			      "frequency": 1,
			      "start_time": 1375483850,
			      "end_time": 1533268480,
			      "auto_recur": false
			    }
			  },
			  {
			    "preapproval_id": 53792406,
			    "preapproval_uri": "https://stage.wepay.com/api/preapproval/53792406/70153995",
			    "state": "cancelled",
			    "manage_uri": "https://stage.wepay.com/preapproval/view/53792406/70153995",
			    "currency": "USD",
			    "payer_name": "John Smith",
			    "payer_email": "john.smith@gmail.com",
			    "create_time": 1374010274,
			    "next_due_time": 1376688674,
			    "last_checkout_id": 2105336128,
			    "last_checkout_time": 1374010512,
			    "preapproval_data": {
			      "account_id": 2111137965,
			      "amount": 40.45,
			      "short_description": "short description",
			      "period": "weekly",
			      "app_fee": 2.30,
			      "fee_payer": "payer",
			      "shipping_fee": 10.24,
			      "frequency": 2,
			      "start_time": 1374010274,
			      "end_time": 1531794904,
			      "auto_recur": true
			    }
			  }
			]
		*/
		/****************************************************************************/
		/**
		 * MODIFY call
		 */
		PreapprovalData newData = new PreapprovalData(); 
		newData.callbackUri = "http://www.yourcallbackuri.com";
		Preapproval myPreapproval = new Preapproval(70569984);
		myPreapproval.modify(newData, myAccessToken);
		//modifications are made, nothing is returned
		/**
			myPreapproval:
			{
			  "preapproval_id": 70569984,
			  "preapproval_uri": "https://stage.wepay.com/api/preapproval/70569984/60ff349a",
			  "state": "new",
			  "manage_uri": "https://stage.wepay.com/preapproval/view/70569984/60ff349a",
			  "currency": "USD",
			  "create_time": 1375483850,
			  "preapproval_data": {
			    "account_id": 0,
			    "amount": 0,
			    "short_description": "sample preapproval",
			    "period": "monthly",
			    "app_fee": 0,
			    "fee_payer": "payer",
			    "callback_uri": "http://www.yourcallbackuri.com",
			    "frequency": 1,
			    "start_time": 1375483850,
			    "end_time": 1533268480,
			    "auto_recur": false
			  }
			}
		 */
		/****************************************************************************/

	}

}
