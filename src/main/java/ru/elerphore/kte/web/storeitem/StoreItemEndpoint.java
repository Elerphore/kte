package ru.elerphore.kte.web.storeitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.elerphore.kte.data.storeitem.StoreItemRequest;
import ru.elerphore.kte.data.storeitem.StoreItemResponse;
import ru.elerphore.kte.services.storeitem.ImpossibleToParseRatingDistributionException;
import ru.elerphore.kte.services.storeitem.StoreItemService;

import javax.jws.WebService;
import java.math.BigDecimal;

/**
* Разработать backend с soap сервисом и дублирующим restFull
* */

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

    /**
     * 3. список товаров (идентификатор, наименование, цена).
    * */

    @Override
    public StoreItemResponse getStoreItems() {
        return storeItemService.getStoreItems();
    }

    /**
     * 4. получение дополнительной информации о товаре
     * */

    @Override
    public StoreItemResponse getStoreItemDescription(StoreItemRequest storeItemRequest) throws ImpossibleToParseRatingDistributionException {
        return storeItemService.getStoreItemDescription(storeItemRequest.getCustomerId(), storeItemRequest.getStoreItemId());
    }

    /**
    * 7. Оценка товара
    * */

    @Override
    public void setCustomerStoreItemRating(StoreItemRequest storeItemRequest) {
        storeItemService.setCustomerStoreItemRating(storeItemRequest.getCustomerId(), storeItemRequest.getStoreItemId(), storeItemRequest.getRating());
    }

    /**
    * 5. Запрос итоговой стоимости
     * */

    @Override
    public BigDecimal getTotalPrice(StoreItemRequest storeItemRequest) {
        return storeItemService.getTotalPrice(storeItemRequest.getCustomerId(), storeItemRequest.getStoreItemId(), storeItemRequest.getAmount());
    }
}
