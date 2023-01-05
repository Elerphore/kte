package ru.elerphore.kte.services.order;

import ru.elerphore.kte.data.customer.CustomerEntity;
import ru.elerphore.kte.data.storeitem.StoreItemEntity;

import java.math.BigDecimal;
import java.util.List;

public class OrderCalculator {
    static public BigDecimal calculateDiscountSum(CustomerEntity customerEntity, StoreItemEntity storeItemEntity, BigDecimal amount) {
        BigDecimal totalPrice = storeItemEntity.getPrice().multiply(amount);
        BigDecimal discount = BigDecimal.ONE;

        if(amount.compareTo(BigDecimal.valueOf(5)) == -1 && customerEntity.getDiscountOne() != null) {
            discount = customerEntity.getDiscountOne().getDiscountValue();
        }

        if(amount.compareTo(BigDecimal.valueOf(5)) == 1 && customerEntity.getDiscountTwo() != null) {
            discount = customerEntity.getDiscountTwo().getDiscountValue();
        }

        return discount;
    }

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

    static public BigDecimal calculateCorrectionPrice(CustomerEntity customerEntity, List<StoreItemEntity> storeItemEntityList) {
        BigDecimal correctedPrice = storeItemEntityList
                .stream()
                .map(orderItem -> calculate(customerEntity, orderItem, orderItem.getAmount()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return correctedPrice;
    }

    static public BigDecimal calculateTotalPrice(CustomerEntity customerEntity, List<StoreItemEntity> storeItemEntityList) {
        return storeItemEntityList
                .stream()
                .map(orderItem -> orderItem.getPrice().multiply(orderItem.getAmount()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }
}