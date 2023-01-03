package ru.elerphore.kte.config;

import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.cxf.Bus;
import ru.elerphore.kte.data.customer.CustomerRepository;
import ru.elerphore.kte.data.discount.DiscountRepository;
import ru.elerphore.kte.data.storeitem.StoreItemRepository;
import ru.elerphore.kte.web.soap.customer.CustomerEndpoint;
import ru.elerphore.kte.web.soap.storeitem.StoreItemEndpoint;

import javax.xml.ws.Endpoint;

@Configuration
public class WebServiceConfig {
    @Autowired
    private Bus bus;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StoreItemRepository storeItemRepository;

    @Autowired
    private DiscountRepository discountRepository;

    @Bean
    public Endpoint customersEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new CustomerEndpoint(customerRepository, discountRepository));
        endpoint.publish("/customers");
        return endpoint;
    }

    @Bean Endpoint storeitemEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new StoreItemEndpoint(storeItemRepository));
        endpoint.publish("/storeitems");
        return endpoint;
    }

}
