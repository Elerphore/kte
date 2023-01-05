package ru.elerphore.kte.web.interfaces;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.elerphore.kte.data.storeitem.StoreItemRequest;
import ru.elerphore.kte.data.storeitem.StoreItemResponse;
import ru.elerphore.kte.services.storeitem.StoreItemService;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.math.BigDecimal;

@WebService(name = "StoreItem", targetNamespace = "http://kte.assigment.application")
public interface StoreItemEndpointInterface {
    StoreItemService storeItemService = null;

    @WebResult(name = "response", targetNamespace = "")
    @RequestWrapper(localName = "StoreItemRequest")
    @ResponseWrapper(className = "StoreItemResponse")
    @GetMapping
    StoreItemResponse getStoreItems();

    @WebResult(name = "response", targetNamespace = "")
    @RequestWrapper(localName = "StoreItemDescriptionRequest")
    @ResponseWrapper(className = "StoreItemResponse")
    @PostMapping(path = "/description")
    StoreItemResponse getStoreItemDescription(
//            @WebParam(name = "customerId", targetNamespace = "http://kte.assigment.application")
//            @RequestParam(name = "customerId")
//            Integer customerId,
//
//            @WebParam(name = "storeItemId", targetNamespace = "http://kte.assigment.application")
//            @RequestParam(name = "storeItemId")
//            Integer storeItemId
            @WebParam(name = "storeItemRequest", targetNamespace = "http://kte.assigment.application") @RequestBody StoreItemRequest storeItemRequest
    );

    @WebResult(name = "response", targetNamespace = "")
    @RequestWrapper(localName = "CustomerStoreItemRatingRequest")
    @ResponseWrapper(className = "StoreItemResponse")
    void setCustomerStoreItemRating(
            @WebParam(name = "customerId", targetNamespace = "http://kte.assigment.application") Integer customerId,
            @WebParam(name = "storeItemId", targetNamespace = "http://kte.assigment.application") Integer storeItemId,
            @WebParam(name = "rating", targetNamespace = "http://kte.assigment.application") Integer rating
    );

    @WebResult(name = "totalPrice", targetNamespace = "")
    @RequestWrapper(localName = "StoreItemTotalPriceRequest")
//    @ResponseWrapper(className = "StoreItemResponse")
    BigDecimal getTotalPrice(
            @WebParam(name = "customerId", targetNamespace = "http://kte.assigment.application") Integer customerId,
            @WebParam(name = "storeItemId", targetNamespace = "http://kte.assigment.application") Integer storeItemId,
            @WebParam(name = "amount", targetNamespace = "http://kte.assigment.application") BigDecimal amount
    );
}
