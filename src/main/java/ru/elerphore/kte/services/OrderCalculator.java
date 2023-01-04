package ru.elerphore.kte.services;

import ru.elerphore.kte.data.customer.CustomerEntity;
import ru.elerphore.kte.data.storeitem.StoreItemEntity;

import java.math.BigDecimal;
import java.util.List;

public class OrderCalculator {
    static public BigDecimal calculate(
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

    static public BigDecimal calculateOrderRequestPrice(CustomerEntity customerEntity, List<StoreItemEntity> storeItemEntityList) {

        BigDecimal correctedPrice = storeItemEntityList
                                    .stream()
                                    .map(orderItem -> calculate(customerEntity, orderItem, orderItem.getAmount()))
                                    .reduce(BigDecimal.ZERO, BigDecimal::add);

        return correctedPrice;
    }
}
