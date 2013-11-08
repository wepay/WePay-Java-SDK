
public class CheckoutCalls {
	
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
		CheckoutData cData = new CheckoutData();
		cData.accountId = 2111137965;
		cData.shortDescription = "sample checkout";
		cData.type = "GOODS";
		cData.amount = 19.99;
		Checkout myCheckout = Checkout.create(cData, myAccessToken);
		/**
			myCheckout: 
			{
			  "checkout_id": 1590803949,
			  "checkout_uri": "https://stage.wepay.com/api/checkout/1590803949/75339d56",
			  "checkout_data": {
			    "account_id": 2111137965,
			    "short_description": "sample checkout",
			    "type": "GOODS",
			    "amount": 19.99
			  }
			}
		 */
		/****************************************************************************/
		/**
		 * FETCH call
		 */
		Checkout myCheckout = Checkout.fetch(1590803949, myAccessToken);
		/**
			myCheckout: 
			{
			  "checkout_id": 1590803949,
			  "checkout_uri": "https://stage.wepay.com/api/checkout/1590803949/75339d56",
			  "state": "new",
			  "currency": "USD",
			  "tax": 0,
			  "create_time": 1375482887,
			  "checkout_data": {
			    "account_id": 2111137965,
			    "short_description": "sample checkout",
			    "type": "GOODS",
			    "amount": 19.99,
			    "app_fee": 0,
			    "fee_payer": "payer",
			    "redirect_uri": "",
			    "auto_capture": true
			  }
			}
		 */
		/****************************************************************************/
		/**
		 * FIND call
		 */
		CheckoutFindData cfData = new CheckoutFindData();
		cfData.accountId = 2111137965;
		Checkout[] foundList = Checkout.find(cfData, myAccessToken);
		/**
		    foundList: 
			[
			  {
			    "checkout_id": 1735306122,
			    "checkout_uri": "https://stage.wepay.com/api/checkout/1735306122/2a73d956",
			    "state": "new",
			    "currency": "USD",
			    "tax": 0,
			    "create_time": 1375483201,
			    "checkout_data": {
			      "account_id": 2111137965,
			      "short_description": "another sample checkout",
			      "type": "GOODS",
			      "amount": 35.5,
			      "app_fee": 0,
			      "fee_payer": "payer",
			      "redirect_uri": "",
			      "auto_capture": true
			    }
			  },
			  {
			    "checkout_id": 1590803949,
			    "checkout_uri": "https://stage.wepay.com/api/checkout/1590803949/75339d56",
			    "state": "new",
			    "currency": "USD",
			    "tax": 0,
			    "create_time": 1375482887,
			    "checkout_data": {
			      "account_id": 2111137965,
			      "short_description": "sample checkout",
			      "type": "GOODS",
			      "amount": 19.99,
			      "app_fee": 0,
			      "fee_payer": "payer",
			      "redirect_uri": "",
			      "auto_capture": true
			    }
			  }
			]
		 */
		/****************************************************************************/
		/**
		 * MODIFY call
		 */
		CheckoutData newData = new CheckoutData(); 
		newData.callbackUri = "http://www.yourcallbackuri.com";
		Checkout myCheckout = new Checkout(1590803949);
		myCheckout.modify(newData, myAccessToken);
		//modifications are made, nothing is returned
		/**
			myCheckout:
			{
			  "checkout_id": 1590803949,
			  "checkout_uri": "https://stage.wepay.com/api/checkout/1590803949/75339d56",
			  "state": "new",
			  "currency": "USD",
			  "tax": 0,
			  "create_time": 1375482887,
			  "checkout_data": {
			    "account_id": 2111137965,
			    "short_description": "sample checkout",
			    "type": "GOODS",
			    "amount": 19.99,
			    "app_fee": 0,
			    "fee_payer": "payer",
			    "redirect_uri": "",
			    "callback_uri": "http://www.yourcallbackuri.com",
			    "auto_capture": true
			  }
			}
		 */
		/****************************************************************************/
		/**
		 * CAPTURE call
		 */
		Checkout myCheckout = new Checkout(1590803949);
		myCheckout.capture();
		//checkout is captured, nothing is returned
		/****************************************************************************/
		/**
		 * REFUND call
		 */
		CheckoutRefundData refund = new CheckoutRefundData();
		refund.refundReason = "shirt did not fit properly";
		refund.amount = 19.99;
		Checkout myCheckout = new Checkout(1590803949);
		myCheckout.refund();
		//checkout is refunded, nothing is returned
		/****************************************************************************/
		/**
		 * CANCEL call
		 */
		Checkout myCheckout = new Checkout(1590803949);
		myCheckout.cancel();
		//checkout is cancelled, nothing is returned
		/****************************************************************************/

	}

}
