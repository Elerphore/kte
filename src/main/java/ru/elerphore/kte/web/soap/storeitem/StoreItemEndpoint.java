package ru.elerphore.kte.web.soap.storeitem;

import ru.elerphore.kte.data.storeitem.StoreItemRequest;
import ru.elerphore.kte.data.storeitem.StoreItemResponse;
import ru.elerphore.kte.services.storeitem.StoreItemService;
import ru.elerphore.kte.web.interfaces.StoreItemEndpointInterface;

import javax.jws.WebService;
import java.math.BigDecimal;

@WebService(serviceName = "StoreItemEndpoint", portName = "StoreItemPort", targetNamespace = "http://kte.assigment.application", endpointInterface = "ru.elerphore.kte.web.interfaces.StoreItemEndpointInterface")
public class StoreItemEndpoint implements StoreItemEndpointInterface {

    private StoreItemService storeItemService;

    public StoreItemEndpoint() { }

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
    public void setCustomerStoreItemRating(Integer customerId, Integer storeItemId, Integer rating) {
        storeItemService.setCustomerStoreItemRating(customerId, storeItemId, rating);
    }

    @Override
    public BigDecimal getTotalPrice(Integer customerId, Integer storeItemId, BigDecimal amount) {
        return storeItemService.getTotalPrice(customerId, storeItemId, amount);
    }
}
