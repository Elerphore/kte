package ru.elerphore.kte.web.soap.customer;

import ru.elerphore.kte.data.customer.CustomerResponse;
import ru.elerphore.kte.services.customer.CustomerService;
import ru.elerphore.kte.web.interfaces.CustomerEndpointInterface;

import javax.jws.WebService;

@WebService(serviceName = "CustomerEndpoint", portName = "CustomerPort", targetNamespace = "http://kte.assigment.application", endpointInterface = "ru.elerphore.kte.web.interfaces.CustomerEndpointInterface")
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
    public CustomerResponse updateCustomerDiscounts(Integer customerId, Integer discountOneId, Integer discountTwoId) {
        return customerService.updateCustomerDiscounts(customerId, discountOneId, discountTwoId);
    }
}
