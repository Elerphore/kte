package ru.elerphore.kte.config;

import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.cxf.Bus;
import ru.elerphore.kte.data.customer.CustomerRepository;
import ru.elerphore.kte.web.soap.customer.CustomerEndpoint;

import javax.xml.ws.Endpoint;

@Configuration
public class WebServiceConfig {
    @Autowired
    private Bus bus;

    @Autowired
    CustomerRepository customerRepository;

    @Bean
    public Endpoint customersEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new CustomerEndpoint(customerRepository));
        endpoint.publish("/customers");
        return endpoint;
    }

}
