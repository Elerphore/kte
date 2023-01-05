package ru.elerphore.kte.web.storeitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.elerphore.kte.data.storeitem.StoreItemRequest;
import ru.elerphore.kte.data.storeitem.StoreItemResponse;
import ru.elerphore.kte.services.storeitem.StoreItemService;

import javax.jws.WebService;
import java.math.BigDecimal;

@WebService(serviceName = "StoreItemEndpoint", portName = "StoreItemPort", targetNamespace = "http://kte.assigment.application", endpointInterface = "ru.elerphore.kte.web.storeitem.StoreItemEndpointInterface")
@RestController
@RequestMapping(path = "/rest/storeitems")
public class StoreItemEndpoint implements StoreItemEndpointInterface {

    private StoreItemService storeItemService;

    public StoreItemEndpoint() { }

    @Autowired
    public StoreItemEndpoint(StoreItemService storeItemService) {
        this.storeItemService = storeItemService;
    }

    @Override
    public StoreItemResponse getStoreItems() {
        return storeItemService.getStoreItems();
    }

    @Override
    public StoreItemResponse getStoreItemDescription(StoreItemRequest storeItemRequest) {
        return storeItemService.getStoreItemDescription(storeItemRequest.getCustomerId(), storeItemRequest.getStoreItemId());
    }

    @Override
    public void setCustomerStoreItemRating(StoreItemRequest storeItemRequest) {
        storeItemService.setCustomerStoreItemRating(storeItemRequest.getCustomerId(), storeItemRequest.getStoreItemId(), storeItemRequest.getRating());
    }

    @Override
    public BigDecimal getTotalPrice(StoreItemRequest storeItemRequest) {
        return storeItemService.getTotalPrice(storeItemRequest.getCustomerId(), storeItemRequest.getStoreItemId(), storeItemRequest.getAmount());
    }
}
