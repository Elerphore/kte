package ru.elerphore.kte.web.storeitem;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    StoreItemResponse getStoreItemDescription(@WebParam(name = "storeItemRequest", targetNamespace = "http://kte.assigment.application") @RequestBody StoreItemRequest storeItemRequest);

    @WebResult(name = "response", targetNamespace = "")
    @RequestWrapper(localName = "CustomerStoreItemRatingRequest")
    @ResponseWrapper(className = "StoreItemResponse")
    @PostMapping(path = "/rating")
    void setCustomerStoreItemRating(@WebParam(name = "storeItemRequest", targetNamespace = "http://kte.assigment.application") @RequestBody StoreItemRequest storeItemRequest);

    @WebResult(name = "totalPrice", targetNamespace = "")
    @RequestWrapper(localName = "StoreItemTotalPriceRequest")
    @ResponseWrapper(className = "StoreItemResponse")
    @GetMapping(path = "/totalprice")
    BigDecimal getTotalPrice(@WebParam(name = "storeItemRequest", targetNamespace = "http://kte.assigment.application") @RequestBody StoreItemRequest storeItemRequest);
}
