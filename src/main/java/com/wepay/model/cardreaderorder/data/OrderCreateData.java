package com.wepay.model.cardreaderorder.data;

public class OrderCreateData {
    public long accountId;
    public int quantity;
    public String shippingMethod;
    public CardReaderData cardReaderData;
    public ShippingContactData shippingContactData;
    public ShippingAddressData shippingAddressData;
    public String callbackURI;
}
