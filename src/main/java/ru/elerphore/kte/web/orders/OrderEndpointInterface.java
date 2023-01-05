package ru.elerphore.kte.web.orders;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.elerphore.kte.data.order.OrderRequest;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "Order", targetNamespace = "http://kte.assigment.application")
public interface OrderEndpointInterface {
    @WebResult(name = "response", targetNamespace = "")
    @RequestWrapper(localName = "OrderRequest")
    @ResponseWrapper(className = "OrderResponse")
    @PostMapping
    String newOrder(@WebParam(name = "orderRequest", targetNamespace = "http://kte.assigment.application") @RequestBody OrderRequest orderRequest) throws UnaccurateTotalPriceSumException;
}
