package ru.elerphore.kte.web.soap.orders;

import ru.elerphore.kte.data.order.OrderRequest;
import ru.elerphore.kte.services.order.OrderService;
import ru.elerphore.kte.web.interfaces.OrderEndpointInterface;

import javax.jws.WebService;
import java.math.BigDecimal;

@WebService(serviceName = "OrderEndpoint", portName = "OrderPort", targetNamespace = "http://kte.assigment.application", endpointInterface = "ru.elerphore.kte.web.interfaces.OrderEndpointInterface")
public class OrderEndpoint implements OrderEndpointInterface {

    private OrderService orderService;

    public OrderEndpoint() { }

    public OrderEndpoint(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String newOrder(Integer customerId, OrderRequest orderRequest, BigDecimal totalPrice) throws Exception {
        return orderService.newOrder(customerId, orderRequest, totalPrice);
    }
}
