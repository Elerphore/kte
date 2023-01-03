package ru.elerphore.kte.web.soap.customer;

import ru.elerphore.kte.data.customer.CustomerResponse;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "Customer", targetNamespace = "http://kte.assigment.application")
public interface CustomerEndpointInterface {
    @WebResult(name = "response", targetNamespace = "")
    @RequestWrapper(localName = "CustomerRequest")
    @ResponseWrapper(className = "CustomerResponse")
    CustomerResponse getCustomers();

    @WebResult(name = "response", targetNamespace = "")
    @RequestWrapper(localName = "CustomerUpdateRequest")
    @ResponseWrapper(className = "CustomerResponse")
    CustomerResponse updateCustomerDiscounts(
            @WebParam(name = "customerId", targetNamespace = "http://kte.assigment.application") Long customerId,
            @WebParam(name = "discountOne", targetNamespace = "http://kte.assigment.application") Long discountOneId,
            @WebParam(name = "discountTwo", targetNamespace = "http://kte.assigment.application") Long discountTwoId
    );
}
