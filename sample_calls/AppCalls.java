
public class AppCalls {
	
	/**
	 * The calls in the main function below are not intended to be executed as they are. 
	 * Think of this code as a template for how you might structure API calls within your 
	 * own code. Below each call is an example of the result of the call (either the call's
	 * response or how the call affect's the objects it is called on. 
	 */
	
	public static void main() {
		
		/****************************************************************************/
		/**
		 * FETCH call
		 */
		App myApp = App.fetch(myAccessToken);
		/**
			myApp:
			{
			  "client_id": 190082,
			  "status": "approved",
			  "app_data": {
			    "theme_object": {}
			  }
			}
		 */
		/****************************************************************************/
		/**
		 * MODIFY call
		 */
		ThemeObjectData theme = new ThemeObjectData();
		theme.name = "My Sample Theme";
		theme.primaryColor = "FFFFFF";
		theme.secondaryColor = "000000";
		theme.backgroundColor = "004C64";
		theme.buttonColor = "0084A0";
		AppData data = new AppData();
		data.themeObject = theme;
		App myApp = new App();
		myApp.modify(data, myAccessToken);
		/**
			myApp:
			{
			  "client_id": 190082,
			  "status": "approved",
			  "app_data": {
			    "theme_object": {
			      "name": "My Sample Theme",
			      "primary_color": "FFFFFF",
			      "secondary_color": "000000",
			      "background_color": "004C64",
			      "button_color": "0084A0"
			    }
			  }
			}
		 */
		/****************************************************************************/

	}

}
