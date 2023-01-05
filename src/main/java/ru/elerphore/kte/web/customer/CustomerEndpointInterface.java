package ru.elerphore.kte.web.customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.elerphore.kte.data.customer.CustomerRequest;
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
    @GetMapping
    CustomerResponse getCustomers();

    @WebResult(name = "response", targetNamespace = "")
    @RequestWrapper(localName = "CustomerUpdateRequest")
    @ResponseWrapper(className = "CustomerResponse")
    @PostMapping
    CustomerResponse updateCustomerDiscounts(@WebParam(name = "customerRequest", targetNamespace = "http://kte.assigment.application") @RequestBody CustomerRequest customerRequest);
}
