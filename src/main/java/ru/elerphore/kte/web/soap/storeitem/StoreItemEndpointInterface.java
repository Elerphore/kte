package ru.elerphore.kte.web.soap.storeitem;

import ru.elerphore.kte.data.storeitem.StoreItemResponse;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "StoreItem", targetNamespace = "http://kte.assigment.application")
public interface StoreItemEndpointInterface {
    @WebResult(name = "response", targetNamespace = "")
    @RequestWrapper(localName = "StoreItemRequest")
    @ResponseWrapper(className = "StoreItemResponse")
    StoreItemResponse getStoreItems();
}
