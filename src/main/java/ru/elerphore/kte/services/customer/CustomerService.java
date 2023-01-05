package ru.elerphore.kte.services.customer;

import org.springframework.stereotype.Service;
import ru.elerphore.kte.data.customer.CustomerEntity;
import ru.elerphore.kte.data.customer.CustomerRepository;
import ru.elerphore.kte.data.customer.CustomerResponse;
import ru.elerphore.kte.data.discount.DiscountEntity;
import ru.elerphore.kte.data.discount.DiscountRepository;

import java.util.Collections;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final DiscountRepository discountRepository;

    public CustomerService(CustomerRepository customerRepository, DiscountRepository discountRepository) {
        this.customerRepository = customerRepository;
        this.discountRepository = discountRepository;
    }

    public CustomerResponse getCustomers() {
        return new CustomerResponse(customerRepository.findAll());
    }

    public CustomerResponse updateCustomerDiscounts(Integer customerId, Integer discountOneId, Integer discountTwoId) {
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

        return new CustomerResponse(Collections.singletonList(customerEntity));
    }

}
