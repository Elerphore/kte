package ru.elerphore.kte.web.soap.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.elerphore.kte.data.customer.CustomerEntity;
import ru.elerphore.kte.data.customer.CustomerRepository;
import ru.elerphore.kte.data.customer.CustomerResponse;

import javax.jws.WebService;
import java.util.List;

@WebService(
        serviceName = "CustomerEndpoint",
        portName = "CustomerPort",
        targetNamespace = "http://kte.assigment.application",
        endpointInterface = "ru.elerphore.kte.web.soap.customer.CustomerEndpointInterface"
)
public class CustomerEndpoint implements CustomerEndpointInterface {
    CustomerRepository customerRepository;

    public CustomerEndpoint() {

    }

    public CustomerEndpoint(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse getCustomers() {
        List<CustomerEntity> customers = customerRepository.findAll();
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setItems(customers);
        return customerResponse;
    }
}
