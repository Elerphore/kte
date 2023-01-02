package ru.elerphore.kte.data.customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerResponse {
    private List<CustomerEntity> items;

    public CustomerResponse(List<CustomerEntity> items) {
        this.items = items;
    }
}
