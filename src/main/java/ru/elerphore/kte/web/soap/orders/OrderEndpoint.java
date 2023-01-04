package ru.elerphore.kte.web.soap.orders;

import org.hibernate.criterion.Order;
import ru.elerphore.kte.data.customer.CustomerEntity;
import ru.elerphore.kte.data.customer.CustomerRepository;
import ru.elerphore.kte.data.order.OrderEntity;
import ru.elerphore.kte.data.order.OrderRepository;
import ru.elerphore.kte.data.order.OrderRequest;
import ru.elerphore.kte.data.orderstoreitem.OrderStoreItemEntity;
import ru.elerphore.kte.data.storeitem.StoreItemEntity;
import ru.elerphore.kte.data.storeitem.StoreItemRepository;
import ru.elerphore.kte.services.OrderCalculator;
import ru.elerphore.kte.services.OrderService;

import javax.jws.WebService;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@WebService(
        serviceName = "OrderEndpoint",
        portName = "OrderPort",
        targetNamespace = "http://kte.assigment.application",
        endpointInterface = "ru.elerphore.kte.web.soap.orders.OrderEndpointInterface"
)
public class OrderEndpoint implements OrderEndpointInterface{
    private OrderService orderService;
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private StoreItemRepository storeItemRepository;

    public OrderEndpoint() {

    }

    public OrderEndpoint(OrderService orderService, OrderRepository orderRepository, CustomerRepository customerRepository, StoreItemRepository storeItemRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.storeItemRepository = storeItemRepository;
    }

    @Override
    public String newOrder(Integer customerId, OrderRequest orderRequest, BigDecimal totalPrice) throws Exception {
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

        String orderNumber = orderService.generateOrderNumber();

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
