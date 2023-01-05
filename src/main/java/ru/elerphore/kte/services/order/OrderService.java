package ru.elerphore.kte.services.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.elerphore.kte.data.customer.CustomerEntity;
import ru.elerphore.kte.data.customer.CustomerRepository;
import ru.elerphore.kte.data.order.OrderEntity;
import ru.elerphore.kte.data.order.OrderRepository;
import ru.elerphore.kte.data.order.OrderRequest;
import ru.elerphore.kte.data.orderstoreitem.OrderStoreItemEntity;
import ru.elerphore.kte.data.storeitem.StoreItemEntity;
import ru.elerphore.kte.data.storeitem.StoreItemRepository;
import ru.elerphore.kte.web.orders.UnaccurateTotalPriceSumException;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final CustomerRepository customerRepository;
    private final StoreItemRepository storeItemRepository;
    private final OrderRepository orderRepository;

    private String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String orderNumberPrefix;
    private Integer orderNumberCounterLastValue = -1;

    @Autowired
    public OrderService(CustomerRepository customerRepository, StoreItemRepository storeItemRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.storeItemRepository = storeItemRepository;
        this.orderRepository = orderRepository;

    }

    public String generateOrderNumber() {
        orderNumberCounterLastValue++;

        return orderNumberPrefix + "000" + orderNumberCounterLastValue.toString();
    }

    private char getRandomChar() {
        Random random = new Random();
        int index = random.nextInt(allowedChars.length());
        return allowedChars.charAt(index);
    }

    @PostConstruct
    private void generateRandomPrefix() {
        orderNumberPrefix = String.join("", Arrays.asList(1, 2).stream().map(n -> "" + getRandomChar()).collect(Collectors.toList()));
    }

    public String newOrder(Integer customerId, OrderRequest orderRequest, BigDecimal totalPrice) throws UnaccurateTotalPriceSumException {
        CustomerEntity customerEntity = customerRepository.findById(customerId).get();

        List<OrderStoreItemEntity> orderStoreItemEntityList = orderRequest.getStoreItemList()
                .stream()
                .map(storeItem -> {
                    StoreItemEntity storeItemEntity = storeItemRepository.findById(storeItem.getId()).get();
                    storeItemEntity.setAmount(storeItem.getAmount());

                    return new OrderStoreItemEntity(storeItemEntity, storeItem.getAmount());
                })
                .collect(Collectors.toList());

        BigDecimal correctedPrice = orderStoreItemEntityList
                .stream()
                .map(orderStoreItemEntity -> {
                    BigDecimal discountSum = OrderCalculator.calculateDiscountSum(customerEntity, orderStoreItemEntity.getStoreItem(), orderStoreItemEntity.getStoreItem().getAmount());
                    BigDecimal totalItemPrice = orderStoreItemEntity.getStoreItem().getPrice().multiply(orderStoreItemEntity.getStoreItem().getAmount());

                    orderStoreItemEntity.setPrice(totalItemPrice);
                    orderStoreItemEntity.setDiscount(discountSum);

                    return totalItemPrice.subtract(discountSum);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal price = OrderCalculator.calculateTotalPrice(customerEntity,
                orderStoreItemEntityList.stream().map(orderStoreItemEntity -> orderStoreItemEntity.getStoreItem()).collect(Collectors.toList()));

        BigDecimal discountSum = price.subtract(correctedPrice);

        if(!price.equals(totalPrice)) {
            throw new UnaccurateTotalPriceSumException();
        }

        String orderNumber = generateOrderNumber();

        final OrderEntity orderEntity = orderRepository.save(new OrderEntity(customerEntity, orderNumber, orderStoreItemEntityList, price, discountSum));

        orderStoreItemEntityList.stream().map(orderStoreItemEntity -> {
                    orderStoreItemEntity.setOrder(orderEntity);
                    return orderStoreItemEntity;
                })
                .collect(Collectors.toList());

        orderRepository.save(orderEntity);

        return orderNumber;
    }

}
