package ru.elerphore.kte.web.soap.customer;

import ru.elerphore.kte.data.customer.CustomerEntity;
import ru.elerphore.kte.data.customer.CustomerRepository;
import ru.elerphore.kte.data.customer.CustomerResponse;
import ru.elerphore.kte.data.discount.DiscountEntity;
import ru.elerphore.kte.data.discount.DiscountRepository;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebService(
        serviceName = "CustomerEndpoint",
        portName = "CustomerPort",
        targetNamespace = "http://kte.assigment.application",
        endpointInterface = "ru.elerphore.kte.web.soap.customer.CustomerEndpointInterface"
)
public class CustomerEndpoint implements CustomerEndpointInterface {
    private CustomerRepository customerRepository;
    private DiscountRepository discountRepository;

    public CustomerEndpoint(CustomerRepository customerRepository, DiscountRepository discountRepository) {
        this.customerRepository = customerRepository;
        this.discountRepository = discountRepository;
    }

    @Override
    public CustomerResponse getCustomers() {
        List<CustomerEntity> customers = customerRepository.findAll();
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setItems(customers);
        return customerResponse;
    }

    @Override
    public CustomerResponse updateCustomerDiscounts(Long customerId, Long discountOneId, Long discountTwoId) {
        CustomerEntity customerEntity = customerRepository.findById(customerId).get();

        if(discountOneId != null) {
            DiscountEntity discount = discountRepository.findById(discountOneId).get();
            customerEntity.setDiscountOne(discount);
        }

        if(discountTwoId != null) {
            DiscountEntity discount = discountRepository.findById(discountTwoId).get();
            customerEntity.setDiscountTwo(discount);
        }

        customerRepository.save(customerEntity);

        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setItems(new ArrayList(Collections.singleton(customerEntity)));

        return customerResponse;
    }
}
