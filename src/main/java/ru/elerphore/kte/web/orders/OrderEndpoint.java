package ru.elerphore.kte.web.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.elerphore.kte.data.order.OrderRequest;
import ru.elerphore.kte.services.order.OrderService;

import javax.jws.WebService;

/**
 * Разработать backend с soap сервисом и дублирующим restFull
 * */

@WebService(serviceName = "OrderEndpoints", portName = "OrderPort", targetNamespace = "http://kte.assigment.application", endpointInterface = "ru.elerphore.kte.web.orders.OrderEndpointInterface")
@RestController
@RequestMapping(path = "/rest/orders")
public class OrderEndpoint implements OrderEndpointInterface {

    private OrderService orderService;

    public OrderEndpoint() { }

    @Autowired
    public OrderEndpoint(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
    * 6. Регистрация продажи
    * */

    @Override
    public String newOrder(OrderRequest orderRequest) throws UnaccurateTotalPriceSumException {
        return orderService.newOrder(orderRequest.getCustomerId(), orderRequest, orderRequest.getTotalPrice());
    }
}
