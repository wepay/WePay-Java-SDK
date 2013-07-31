WePay-Java-SDK
================================

The lib folder contains the jars necessary to use the SDK to make API calls to WePay. The src folder contains all source code used to build the the WePayAPI.jar. 

To use this SDK, first add the WePayAPI.jar to your project. You'll also need to add GSON and JSON jars as well (these are included in the lib folder). You'll also need to import the necessary classes and packages into your own code. 
```java
import com.wepay.WePay;                     // WePay object needed for API initialization
import com.wepay.model.*;                   // contains call classes and all API call functions
import com.wepay.model.data.*;              // contains all data objects needed for making calls
import com.wepay.net.WePayResource;         // network resource used to execute calls
import com.wepay.exception.WePayException;  // handles WePay exceptions
import org.json.*;                          // SDK uses JSONObjects and JSONArrays when passing API call parameters
import com.google.gson.*;                   // SDK uses GSON for building objects from API call responses
```
Before making actual API calls, you'll need to initialize a new WePay object with your WePay application's client ID, client secret, and whether you want to use the stage environment (true) or the production environment (false).
```java	
WePay wepay = new WePay();
wepay.initialize(myClientId, myClientSecret, useStageEnv);
```	
Once the WePay object is initialized, you can make all API calls described by the full documentation on WePay's website. For a full listing of available API calls, visit https://www.wepay.com/developer and explore the API Calls section. 

API calls take two arguments, a data object argument and an access_token argument. For API calls where authorization is needed, you'll need to include the access_token argument appropriately; for calls that don't require authorization, you can set access_token to null. 

The data objects facilitate the passing of valid parameters to API call classes. Most data objects are designed specifically as parameters to certain types of API calls. Other data objects are utilized by multiple API calls. Explore the source code in the model folder and data sub-folder to see which types of calls take certain data object parameters.

The following sample code shows how you can make an "/account/create" API call and how you can use the returned object to obtain information about the account. 
```java
AccountData data = new AccountData();
data.name = "Test Person";
data.description = "This is an example account";
data.type = "personal";
/** 
 *   You can include as many parameters as you'd like to the data 
 *   object before executing the API call. However required parameters 
 *   must be included or the call will fail.
 */
Account newAccount = Account.create(data, myAccessToken);
/**
 *   You can now call instance methods on your newAccount object to 
 *   obtain information about the account. 
 */
Long accountId = newAccount.getAccount_id(); 
String verificationState = newAccount.getVerification_state();
```	
All API calls use this sort of format. There are many types of data objects that are necessary for different API calls, so take a few minutes to browse the source code and see how API call classes use different data objects. 

A demo of the WePay API Java SDK can be found at WeFarm, an online demo marketplace built on the WePay API using this SDK. WeFarm is live online at http://wefarm.herokuapp.com/. You can also find the Java source code for WeFarm at https://github.com/wepay/WeFarm-Java to see how WeFarm uses this SDK to make WePay API calls. 

For further information, visit https://www.wepay.com/developer. 
