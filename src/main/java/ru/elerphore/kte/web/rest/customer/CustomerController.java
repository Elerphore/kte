package ru.elerphore.kte.web.rest.customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.elerphore.kte.data.customer.CustomerResponse;
import ru.elerphore.kte.services.customer.CustomerService;
import ru.elerphore.kte.web.interfaces.CustomerEndpointInterface;

@RestController
@RequestMapping(path = "/rest/customers")
public class CustomerController implements CustomerEndpointInterface {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public CustomerResponse getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping
    public CustomerResponse updateCustomerDiscounts(Integer customerId, Integer discountOneId, Integer discountTwoId) {
        return customerService.updateCustomerDiscounts(customerId, discountOneId, discountTwoId);
    }

}
