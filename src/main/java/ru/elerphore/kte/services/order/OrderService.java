package ru.elerphore.kte.services.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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
import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OrderService {
    private final CustomerRepository customerRepository;
    private final StoreItemRepository storeItemRepository;
    private final OrderRepository orderRepository;

    private final String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String orderNumberPrefix;
    private Integer orderNumberCounterLastValue = -1;

    @Autowired
    public OrderService(CustomerRepository customerRepository, StoreItemRepository storeItemRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.storeItemRepository = storeItemRepository;
        this.orderRepository = orderRepository;

    }

    private String generateOrderNumber() {
        orderNumberCounterLastValue++;

        return orderNumberPrefix + "000" + orderNumberCounterLastValue;
    }

    private char getRandomChar() {
        Random random = new Random();
        int index = random.nextInt(allowedChars.length());
        return allowedChars.charAt(index);
    }

    /**
     * Номер чека уникален в пределах суток, состоит из пяти цифр с лидирующими нулями, каждый день нумерация начинается с 00100 и растет последовательно (+1 для каждого чека).
     * */

    @PostConstruct
    private void generateRandomPrefix() {
        orderNumberPrefix = Stream.of(1, 2).map(n -> "" + getRandomChar()).collect(Collectors.joining(""));
    }

    private List<OrderStoreItemEntity> getOrderStoreItemEnitiesList(OrderRequest orderRequest) {
        return orderRequest.getStoreItemList()
                .stream()
                .map(storeItem -> {
                    StoreItemEntity storeItemEntity = storeItemRepository.findById(storeItem.getId()).get();
                    storeItemEntity.setAmount(storeItem.getAmount());

                    return new OrderStoreItemEntity(storeItemEntity, storeItem.getAmount());
                })
                .collect(Collectors.toList());
    }

    private Pair<BigDecimal, BigDecimal> getOrderPrices(List<OrderStoreItemEntity> orderStoreItemEntityList, CustomerEntity customerEntity) {
        return orderStoreItemEntityList
                .stream()
                .map(orderStoreItemEntity -> {
                    BigDecimal discountSum = OrderCalculator.calculateItemDiscountPercentage(customerEntity, orderStoreItemEntity.getStoreItem(), orderStoreItemEntity.getStoreItem().getAmount());
                    BigDecimal totalItemPrice = OrderCalculator.calculateItemTotalPrice(orderStoreItemEntity.getStoreItem(), orderStoreItemEntity.getStoreItem().getAmount());

                    BigDecimal discountPrice = OrderCalculator.calculateDiscountItemSum(totalItemPrice, discountSum);

                    orderStoreItemEntity.setPrice(totalItemPrice);
                    orderStoreItemEntity.setDiscount(discountPrice);
                    return Pair.of(totalItemPrice, discountPrice);
                })
                .reduce(Pair.of(BigDecimal.ZERO, BigDecimal.ZERO), (pair, bigDecimalBigDecimalPair) -> Pair.of(pair.getFirst().add(bigDecimalBigDecimalPair.getFirst()), pair.getSecond().add(bigDecimalBigDecimalPair.getSecond())));
    }

    public String newOrder(Integer customerId, OrderRequest orderRequest, BigDecimal requestTotalPrice) throws UnaccurateTotalPriceSumException {
        CustomerEntity customerEntity = customerRepository.findById(customerId).get();

        List<OrderStoreItemEntity> orderStoreItemEntityList = getOrderStoreItemEnitiesList(orderRequest);

        Pair<BigDecimal, BigDecimal> orderPrices = getOrderPrices(orderStoreItemEntityList, customerEntity);

        BigDecimal totalPrice = orderPrices.getFirst().subtract(orderPrices.getSecond());

        if(!requestTotalPrice.equals(totalPrice)) {
            throw new UnaccurateTotalPriceSumException();
        }

        String orderNumber = generateOrderNumber();

        final OrderEntity orderEntity = orderRepository.save(new OrderEntity(customerEntity, orderNumber, orderStoreItemEntityList, orderPrices.getSecond(), orderPrices.getFirst()));

        orderStoreItemEntityList = orderStoreItemEntityList.stream().peek(orderStoreItemEntity -> orderStoreItemEntity.setOrder(orderEntity)).collect(Collectors.toList());
        orderEntity.setOrderStoreItemEntityList(orderStoreItemEntityList);

        orderRepository.save(orderEntity);

        return orderNumber;
    }
}
