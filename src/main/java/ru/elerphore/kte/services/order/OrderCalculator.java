package ru.elerphore.kte.services.order;

import ru.elerphore.kte.data.customer.CustomerEntity;
import ru.elerphore.kte.data.storeitem.StoreItemEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class OrderCalculator {
    static public BigDecimal calculateItemDiscountSum(CustomerEntity customerEntity, StoreItemEntity storeItemEntity, BigDecimal amount) {
        BigDecimal discount = BigDecimal.ZERO;

        /**
        * При заказе 5 и более единиц товара применяется индивидуальная скидка 2 (если не равна 0). При заказе меньшего числа единиц или отсутствии индивидуальной скидки 2 применяется индивидуальная скидка 1.
        * */

        if(amount.compareTo(BigDecimal.valueOf(5)) < 0 && customerEntity.getDiscountOne() != null || amount.compareTo(BigDecimal.valueOf(5)) >= 0 && customerEntity.getDiscountTwo() == null) {
            discount = customerEntity.getDiscountOne().getDiscountValue();
        }

        if(amount.compareTo(BigDecimal.valueOf(5)) >= 0 && customerEntity.getDiscountTwo() != null) {
            discount = customerEntity.getDiscountTwo().getDiscountValue();
        }

        if(storeItemEntity.getDiscount() != null) {
            discount.add(storeItemEntity.getDiscount().getDiscountValue());
        }

        /**
         * Индивидуальная скидка суммируется со скидкой на товар, но общая скидка не должна превышать 18%.
         * */

        if(discount.compareTo(BigDecimal.valueOf(18)) > 0) {
            discount = BigDecimal.valueOf(18);
        }

        return discount;
    }

    static public BigDecimal calculateItemTotalPrice(StoreItemEntity storeItemEntity, BigDecimal amount) {
        return storeItemEntity.getPrice().multiply(amount);
    }

    static public BigDecimal calculateItemSumPrice(BigDecimal totalPrice, BigDecimal discount) {

        BigDecimal discountPrice = totalPrice;

        if(discount.compareTo(BigDecimal.ZERO) > 0) {
            discountPrice = totalPrice.multiply(discount).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        }

        return totalPrice.subtract(discountPrice);
    }

    static public BigDecimal calculate(CustomerEntity customerEntity, StoreItemEntity storeItemEntity, BigDecimal amount) {
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
