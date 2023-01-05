package ru.elerphore.kte.web.rest.order;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.elerphore.kte.data.order.OrderRequest;
import ru.elerphore.kte.services.order.OrderService;
import ru.elerphore.kte.web.interfaces.OrderEndpointInterface;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "/rest/orders")
public class OrderController implements OrderEndpointInterface {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public String newOrder(Integer customerId, OrderRequest orderRequest, BigDecimal totalPrice) throws Exception {
        return orderService.newOrder(customerId, orderRequest, totalPrice);
    }

}
