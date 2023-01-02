package ru.elerphore.kte.web.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.elerphore.kte.data.customer.CustomerEntity;
import ru.elerphore.kte.data.customer.CustomerRepository;
import ru.elerphore.kte.data.customer.CustomerResponse;

import java.util.List;

@Endpoint
public class CustomerEndpoint {
    CustomerRepository customerRepository;

    @Autowired
    public CustomerEndpoint(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @ResponsePayload
    public CustomerResponse GetCustomerList() {
        List<CustomerEntity> customers = customerRepository.findAll();

        return new CustomerResponse(customers);
    }
}
