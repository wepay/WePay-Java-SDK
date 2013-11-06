
public class CreditCardCalls {
	
	/**
	 * The calls in the main function below are not intended to be executed as they are. 
	 * Think of this code as a template for how you might structure API calls within your 
	 * own code. Below each call is an example of the result of the call (either the call's
	 * response or how the call affect's the objects it is called on. 
	 */
	
	public static void main() {
		
		/****************************************************************************/
		/**
		 * AUTHORIZE call
		 */
		CreditCard myCreditCard = new CreditCard(2476834062);
		myCreditCard.authorize(myAccessToken);
		/**
			myCreditCard: 
			{
			  "credit_card_id": 2476834062,
			  "credit_card_name": "MasterCard xxxxxx0633",
			  "state": "authorized",
			  "user_name": "John Smith",
			  "email": "john.smith@gmail.com",
			  "create_time": 1373414206
			}
		 */
		/****************************************************************************/
		/**
		 * FETCH call
		 */
		CreditCard myCreditCard = CreditCard.fetch(2476834062, myAccessToken);
		/**
			myCreditCard: 
			{
			  "credit_card_id": 2476834062,
			  "credit_card_name": "MasterCard xxxxxx0633",
			  "state": "authorized",
			  "user_name": "John Smith",
			  "email": "john.smith@gmail.com",
			  "create_time": 1373414206
			}
		 */
		/****************************************************************************/
		/**
		 * FIND call
		 */
		CreditCard[] foundList = CreditCard.find(null, myAccessToken);
		/**
		    foundList: 
			[
			  {
			    "credit_card_id": 2476834062,
			    "credit_card_name": "MasterCard xxxxxx0633",
			    "state": "authorized",
			    "user_name": "John Smith",
			    "email": "john.smith@gmail.com",
			    "create_time": 1373414206
			  },
			  {
			    "credit_card_id": 6384023873,
			    "credit_card_name": "MasterCard xxxxxx0381",
			    "state": "authorized",
			    "user_name": "John Smith",
			    "email": "john.smith@gmail.com",
			    "create_time": 1372493226
			  }
			]
		*/
		/****************************************************************************/
		/**
		 * Delete call
		 */
		CreditCard myCreditCard = new CreditCard();
		myCreditCard.delete(myAccessToken);
		//credit card associated with this access token is deleted, nothing is returned
		/****************************************************************************/

	}

}
