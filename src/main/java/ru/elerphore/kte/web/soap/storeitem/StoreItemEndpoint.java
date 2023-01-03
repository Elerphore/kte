package ru.elerphore.kte.web.soap.storeitem;

import ru.elerphore.kte.data.storeitem.StoreItemEntity;
import ru.elerphore.kte.data.storeitem.StoreItemRepository;
import ru.elerphore.kte.data.storeitem.StoreItemResponse;

import javax.jws.WebService;
import java.util.List;

@WebService(
        serviceName = "StoreItemEndpoint",
        portName = "StoreItemPort",
        targetNamespace = "http://kte.assigment.application",
        endpointInterface = "ru.elerphore.kte.web.soap.storeitem.StoreItemEndpointInterface"
)
public class StoreItemEndpoint implements StoreItemEndpointInterface {
    private StoreItemRepository storeItemRepository;

    public StoreItemEndpoint() {

    }

    public StoreItemEndpoint(StoreItemRepository storeItemRepository) {
        this.storeItemRepository = storeItemRepository;
    }

    @Override
    public StoreItemResponse getStoreItems() {
        List<StoreItemEntity> storeItems = storeItemRepository.findAll();
        StoreItemResponse storeItemResponse = new StoreItemResponse();
        storeItemResponse.setItems(storeItems);

        return storeItemResponse;
    }
}
