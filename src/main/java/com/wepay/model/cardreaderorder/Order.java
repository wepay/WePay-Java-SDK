package com.wepay.model.cardreaderorder;

import com.wepay.exception.WePayException;
import com.wepay.model.cardreaderorder.data.OrderCreateData;
import com.wepay.model.cardreaderorder.data.ShippingAddressData;
import com.wepay.model.cardreaderorder.data.ShippingContactData;
import com.wepay.net.WePayResource;
import org.json.JSONObject;

import java.io.IOException;

/**
 * This is an order for a card reader. The operations that are allowed are: create, find
 */
public class Order extends WePayResource {

    /**
     * Merchant's account id
     */
    protected long accountId;

    protected int quantity;

    protected String callbackURI;
    protected String model;
    protected String shippingMethod;
    protected ShippingContactData shippingContactData;
    protected ShippingAddressData shippingAddressData;

    protected long orderId;
    protected String status;
    protected String trackingCode;
    protected String trackingService;
    protected OrderTracker order;

    public Order(OrderCreateData orderCreateData) {
        this.accountId = orderCreateData.accountId;
        this.quantity = orderCreateData.quantity;
        this.callbackURI = orderCreateData.callbackURI;
        this.model = orderCreateData.model;
        this.shippingMethod = orderCreateData.shippingMethod;
        this.shippingContactData = orderCreateData.shippingContactData;
        this.shippingAddressData = orderCreateData.shippingAddressData;
    }

    public static Order create(OrderCreateData orderCreateData, String accessToken) throws IOException, WePayException {
        JSONObject params = new JSONObject();
        params.put("account_id", orderCreateData.accountId);
        params.put("quantity", orderCreateData.quantity);
        params.put("callback_uri", orderCreateData.callbackURI);
        params.put("model", orderCreateData.model);
        params.put("shipping_method", orderCreateData.shippingMethod);
        params.put("shipping_contact", orderCreateData.shippingContactData);
        params.put("shipping_address", orderCreateData.shippingAddressData);

        String response = request("/order/card_reader/create", params, accessToken);
        Order createdOrder = gson.fromJson(response, Order.class);

        return createdOrder;
    }

    public static Order find(long orderId, String accessToken) throws IOException, WePayException {
        JSONObject params = new JSONObject();
        params.put("order_id", orderId);
        String response = request("/order/card_reader", params, accessToken);
        Order order = gson.fromJson(response, Order.class);
        return order;
    }

    public class OrderTracker {
        private String trackingService;
        private String trackingCode;
    }

    public String getTrackingService() {
        return order != null ? order.trackingService : "";
    }

    public String getTrackingCode() {
        return order != null ? order.trackingCode : "";
    }

    public long getAccountId() {
        return accountId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getModel() {
        return model;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public ShippingContactData getShippingContactData() {
        return shippingContactData;
    }

    public ShippingAddressData getShippingAddressData() {
        return shippingAddressData;
    }

    public long getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public String getContactName() {
        return shippingContactData.name;
    }

    public String getContactCompany() {
        return shippingContactData.company;
    }

    public String getContactEmail() {
        return shippingContactData.email;
    }

    public String getContactPhone() {
        return shippingContactData.phone;
    }

    public String getStreetAddress1() {
        return shippingAddressData.street1;
    }

    public String getStreetAddress2() {
        return shippingAddressData.street2;
    }

    public String getCity() {
        return shippingAddressData.city;
    }

    public String getState() {
        return shippingAddressData.state;
    }

    public int getZip() {
        return shippingAddressData.zip;
    }



}
