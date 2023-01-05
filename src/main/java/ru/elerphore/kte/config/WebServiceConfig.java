package ru.elerphore.kte.config;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.elerphore.kte.services.customer.CustomerService;
import ru.elerphore.kte.services.order.OrderService;
import ru.elerphore.kte.services.storeitem.StoreItemService;
import ru.elerphore.kte.web.customer.CustomerEndpoint;
import ru.elerphore.kte.web.orders.OrderEndpoint;
import ru.elerphore.kte.web.storeitem.StoreItemEndpoint;

import javax.xml.ws.Endpoint;

@Configuration
public class WebServiceConfig {

    private final Bus bus;
    private final StoreItemService storeItemService;
    private final CustomerService customerService;
    private final OrderService orderService;

    @Autowired
    public WebServiceConfig(Bus bus, StoreItemService storeItemService, CustomerService customerService, OrderService orderService) {
        this.bus = bus;
        this.storeItemService = storeItemService;
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @Bean
    public Endpoint customersEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new CustomerEndpoint(customerService));
        endpoint.publish("/customers");
        return endpoint;
    }

    @Bean Endpoint storeitemEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new StoreItemEndpoint(storeItemService));
        endpoint.publish("/storeitems");
        return endpoint;
    }

    @Bean Endpoint order() {
        EndpointImpl endpoint = new EndpointImpl(bus, new OrderEndpoint(orderService));
        endpoint.publish("/orders");
        return endpoint;
    }

}
