package ru.elerphore.kte.services;

import ru.elerphore.kte.data.customer.CustomerEntity;
import ru.elerphore.kte.data.storeitem.StoreItemEntity;

import java.math.BigDecimal;

public class OrderCalculator {
    public BigDecimal calculate(
            CustomerEntity customerEntity, StoreItemEntity storeItemEntity, BigDecimal amount
    ) {
        BigDecimal totalPrice = storeItemEntity.getPrice().multiply(amount);
        BigDecimal discount = null;

        if(amount.compareTo(BigDecimal.valueOf(5)) == -1 && customerEntity.getDiscountOne() != null) {
            discount = customerEntity.getDiscountOne().getDiscountValue();
        }

        if(amount.compareTo(BigDecimal.valueOf(5)) == 1 && customerEntity.getDiscountTwo() != null) {
            discount = customerEntity.getDiscountTwo().getDiscountValue();
        }

        if(discount != null) {
            totalPrice = totalPrice.multiply(discount).divide(BigDecimal.valueOf(100));
        }

        return totalPrice;
    };
}
