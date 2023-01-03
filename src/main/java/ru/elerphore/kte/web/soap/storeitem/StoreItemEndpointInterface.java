package ru.elerphore.kte.web.soap.storeitem;

import ru.elerphore.kte.data.storeitem.StoreItemResponse;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.math.BigDecimal;

@WebService(name = "StoreItem", targetNamespace = "http://kte.assigment.application")
public interface StoreItemEndpointInterface {
    @WebResult(name = "response", targetNamespace = "")
    @RequestWrapper(localName = "StoreItemRequest")
    @ResponseWrapper(className = "StoreItemResponse")
    StoreItemResponse getStoreItems();

    @WebResult(name = "response", targetNamespace = "")
    @RequestWrapper(localName = "StoreItemDescriptionRequest")
    @ResponseWrapper(className = "StoreItemResponse")
    StoreItemResponse getStoreItemDescription(
            @WebParam(name = "customerId", targetNamespace = "http://kte.assigment.application") Integer customerId,
            @WebParam(name = "storeItemId", targetNamespace = "http://kte.assigment.application") Integer storeItemId
    );

    @WebResult(name = "response", targetNamespace = "")
    @RequestWrapper(localName = "CustomerStoreItemRatingRequest")
    @ResponseWrapper(className = "StoreItemResponse")
    void setCustomerStoreItemRating(
            @WebParam(name = "customerId", targetNamespace = "http://kte.assigment.application") Integer customerId,
            @WebParam(name = "storeItemId", targetNamespace = "http://kte.assigment.application") Integer storeItemId,
            @WebParam(name = "rating", targetNamespace = "http://kte.assigment.application") Integer rating
    );

    @WebResult(name = "response", targetNamespace = "")
    @RequestWrapper(localName = "StoreItemTotalPriceRequest")
//    @ResponseWrapper(className = "StoreItemResponse")
    BigDecimal getTotalPrice(
            @WebParam(name = "customerId", targetNamespace = "http://kte.assigment.application") Integer customerId,
            @WebParam(name = "storeItemId", targetNamespace = "http://kte.assigment.application") Integer storeItemId,
            @WebParam(name = "amount", targetNamespace = "http://kte.assigment.application") BigDecimal amount
    );
}
