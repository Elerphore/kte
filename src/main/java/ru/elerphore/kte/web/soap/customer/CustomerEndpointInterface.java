package ru.elerphore.kte.web.soap.customer;

import ru.elerphore.kte.data.customer.CustomerResponse;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(
        name = "Customer",
        targetNamespace = "http://kte.assigment.application"
)
public interface CustomerEndpointInterface {
    @WebResult(name = "", targetNamespace = "")
    @RequestWrapper(
            localName = "CustomerRequest",
            targetNamespace = "http://kte.assigment.application",
            className = "ru.elerphore.kte.data.customer.CustomerRequest")
    @WebMethod(action = "getCustomers")
    @ResponseWrapper(
            localName = "CustomerResponses",
            targetNamespace = "http://kte.assigment.application",
            className = "CustomerResponse")
    CustomerResponse getCustomers();
}
