package ru.elerphore.kte.web.customer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.elerphore.kte.data.customer.CustomerRequest;
import ru.elerphore.kte.data.customer.CustomerResponse;
import ru.elerphore.kte.services.customer.CustomerService;

import javax.jws.WebService;

@WebService(serviceName = "CustomerEndpoint", portName = "CustomerPort", targetNamespace = "http://kte.assigment.application", endpointInterface = "ru.elerphore.kte.web.customer.CustomerEndpointInterface")
@RestController
@RequestMapping(path = "/rest/customers")
public class CustomerEndpoint implements CustomerEndpointInterface {

    private CustomerService customerService;

    public CustomerEndpoint() { }

    public CustomerEndpoint(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public CustomerResponse getCustomers() {
        return customerService.getCustomers();
    }

    @Override
    public CustomerResponse updateCustomerDiscounts(CustomerRequest customerRequest) {
        return customerService.updateCustomerDiscounts(customerRequest.getCustomerId(), customerRequest.getDiscountOneId(), customerRequest.getDiscountTwoId());
    }
}
