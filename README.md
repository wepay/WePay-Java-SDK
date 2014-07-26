WePay-Java-SDK
================================

Note
================================
This WePay-Java-SDK is for <a href = "https://www.wepay.com/developer/version/2014-01-08">WePay Api Version 2014-01-08</a>. The jar for the previous API version (2011-01-15) can be found in the lib folder and is named wepay-2011-01-15.jar.

Overview
================================
To use this SDK, first add the WePay jar (com.wepay.jar) to your project. You'll need to add GSON and JSON jars as well (gson-2.2.4.jar and java-json.jar are also included in the lib folder). 

The lib folder contains the jars necessary to use the SDK to make API calls to WePay. The src folder contains all source code used to build the WePay jar (com.wepay.jar). You'll also need to import the necessary classes and packages into your own code. 

```java
import com.wepay.WePay;                     // WePay object needed for API initialization
import com.wepay.model.*;                   // contains call classes and all API call functions
import com.wepay.model.data.*;              // contains all data objects needed for making calls
import com.wepay.net.WePayResource;         // network resource used to execute calls
import com.wepay.exception.WePayException;  // handles WePay exceptions
import org.json.*;                          // SDK uses JSON when handling API call parameters
import com.google.gson.*;                   // SDK uses GSON for building objects from API responses
```
Before making actual API calls, you'll need to initialize a new WePay object with your WePay application's client ID, client secret, and whether you want to use the stage environment (true) or the production environment (false).
```java	
WePay wepay = new WePay();
wepay.initialize(myClientId, myClientSecret, useStageEnv);
```	
Once the WePay object is initialized, you can make all of the API calls described by the documentation on WePay's website. For a full listing of available API calls, visit https://www.wepay.com/developer and explore the API Calls section. 

API calls through the Java SDK generally take two arguments, a data object argument and an accessToken argument. For API calls where authorization is needed, you'll need to include the accessToken argument appropriately; for calls that don't require authorization, you can set accessToken to null. 

Data objects facilitate the passing of valid parameters to API call classes. Most data objects are designed specifically as parameters to certain types of API calls. Other data objects are utilized by multiple API calls. You can learn what types of data objects are taken as arguments by API call functions in the <a href="https://github.com/wepay/WePay-Java-SDK/blob/master/APICalls.txt">APICalls.txt</a> file. This file contains a listing of all available API calls available through this SDK, their return types, and which data object parameters each call takes. 

You can find examples from all API call classes in the <a href="https://github.com/wepay/WePay-Java-SDK/tree/master/sample_calls">sample_calls</a> folder. Each file in that folder contains an example of each of its available API calls and the effect of that call. You should structure the API calls in your code after these examples. It may also be beneficial to explore the source code in the <a href="https://github.com/wepay/WePay-Java-SDK/tree/master/src/main/java/com/wepay/model">model</a> and <a href="https://github.com/wepay/WePay-Java-SDK/tree/master/src/main/java/com/wepay/model/data">data</a> folders to learn a bit more about the way data objects are used.

You *must* include all *required* parameters to successfully execute API calls. You can also include as many optional parameters as you'd like to the data object before executing the API call. For a full listing of required parameters for each call, visit the API documentation. 

The following examples show how to make a few simple API calls and how you can use the returned objects to obtain information. You can find more examples in the <a href="https://github.com/wepay/WePay-Java-SDK/tree/master/sample_calls">sample_calls</a> folder.
```java
// create a new account
AccountData aData = new AccountData();
aData.name = "Test Person";                          // required parameter for this API call
aData.description = "This is an example account";    // required parameter for this API call
aData.type = "personal";                             // optional parameter for this API call
Account newAccount = Account.create(aData, myAccessToken);

// get an accessToken for the new account
OAuth2Data data = new OAuth2Data();
data.redirectUri = "http://www.mywebsite.com/oauth2/";
data.scope = "manage_accounts,collect_payments,view_user,preapprove_payments,send_money";
String sendUserToThisURL = OAuth2.authorize(data, null);
//send user to the returned URL to complete OAuth2 authorization form

//on completion of authorization, user is redirected to redirectUri with a code parameter
//once you have the code you can exchange it for an access token
OAuth2Data data = new OAuth2Data();
data.redirectUri = "http://www.mywebsite.com/oauth2/";
data.code = code;
String userNewAccessToken = OAuth2.token(data, null);

// create a new checkout
CheckoutData cData = new CheckoutData();
cData.accountId = newAccount.getAccountId();       // use a returned object to access information
cData.shortDescription = "Soccer Ball Purchase";
cData.type = "GOODS";
cData.amount = 29.95;
cData.appFee = 0.50;
cData.feePayer = "payee";
Checkout newCheckout = Checkout.create(cData, userNewAccessToken);
```
A demo of the WePay API Java SDK can be found at WeFarm, an online demo marketplace built on the WePay API using this SDK. WeFarm is live online at http://wefarm.herokuapp.com/. You can also find the Java source code for WeFarm at https://github.com/wepay/WeFarm-Java to see how WeFarm uses this SDK to execute WePay API calls. 

For further information, visit https://www.wepay.com/developer. 
