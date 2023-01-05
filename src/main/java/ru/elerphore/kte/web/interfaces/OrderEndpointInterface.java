package ru.elerphore.kte.web.interfaces;

import ru.elerphore.kte.data.order.OrderRequest;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.math.BigDecimal;

@WebService(name = "Order", targetNamespace = "http://kte.assigment.application")
public interface OrderEndpointInterface {
    @WebResult(name = "response", targetNamespace = "")
    @RequestWrapper(localName = "OrderRequest")
    @ResponseWrapper(className = "OrderResponse")
    String newOrder(
            @WebParam(name = "customerId", targetNamespace = "http://kte.assigment.application") Integer customerId,
            @WebParam(name = "orderRequest", targetNamespace = "http://kte.assigment.application") OrderRequest orderRequest,
            @WebParam(name = "totalPrice", targetNamespace = "http://kte.assigment.application") BigDecimal totalPrice
    ) throws Exception;
}
