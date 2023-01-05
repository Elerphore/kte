package ru.elerphore.kte.web.rest.storeitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.elerphore.kte.data.storeitem.StoreItemRequest;
import ru.elerphore.kte.data.storeitem.StoreItemResponse;
import ru.elerphore.kte.services.storeitem.StoreItemService;
import ru.elerphore.kte.web.interfaces.StoreItemEndpointInterface;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "/rest/storeitems")
public class StoreItemController implements StoreItemEndpointInterface {

    private final StoreItemService storeItemService;

    @Autowired
    public StoreItemController(StoreItemService storeItemService) {
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
    @PostMapping
    public void setCustomerStoreItemRating(Integer customerId, Integer storeItemId, Integer rating) {
        storeItemService.setCustomerStoreItemRating(customerId, storeItemId, rating);
    }

    @Override
    @GetMapping(path = "/totalprice")
    public BigDecimal getTotalPrice(Integer customerId, Integer storeItemId, BigDecimal amount) {
        return storeItemService.getTotalPrice(customerId, storeItemId, amount);
    }
}
