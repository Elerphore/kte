package ru.elerphore.kte.services.storeitem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.elerphore.kte.data.customer.CustomerEntity;
import ru.elerphore.kte.data.customer.CustomerRepository;
import ru.elerphore.kte.data.storeitem.StoreItem;
import ru.elerphore.kte.data.storeitem.StoreItemEntity;
import ru.elerphore.kte.data.storeitem.StoreItemRepository;
import ru.elerphore.kte.data.storeitem.StoreItemResponse;
import ru.elerphore.kte.data.storeitemcustomerrating.StoreItemCustomerRatingEntity;
import ru.elerphore.kte.data.storeitemcustomerrating.StoreItemCustomerRatingRepository;
import ru.elerphore.kte.services.order.OrderCalculator;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StoreItemService {
    private final StoreItemRepository storeItemRepository;
    private final CustomerRepository customerRepository;
    private final StoreItemCustomerRatingRepository storeItemCustomerRatingRepository;
    private final EntityManager entityManager;

    public StoreItemService(StoreItemRepository storeItemRepository, CustomerRepository customerRepository, StoreItemCustomerRatingRepository storeItemCustomerRatingRepository, EntityManager entityManager) {
        this.customerRepository = customerRepository;
        this.storeItemRepository = storeItemRepository;
        this.storeItemCustomerRatingRepository = storeItemCustomerRatingRepository;
        this.entityManager = entityManager;
    }

    public StoreItemResponse getStoreItems() {
        List<StoreItemEntity> storeItemEntities = storeItemRepository.findAll();
        List<StoreItem> storeItems = storeItemEntities
                .stream()
                .map(storeItem ->
                        new StoreItem(storeItem.getId(), storeItem.getName(), storeItem.getPrice())
                )
                .collect(Collectors.toList());

        StoreItemResponse storeItemResponse = new StoreItemResponse();
        storeItemResponse.setItems(storeItems);

        return storeItemResponse;
    }

    public StoreItemResponse getStoreItemDescription(Integer customerId, Integer storeItemId) {

        String query =
                "select cast(json_object_agg(rating, amount) as text) from (select distinct count(rating) as amount from store_item_customer_rating where storeitem_id = 1 group by rating) as ratingAmount, store_item_customer_rating where storeitem_id = " + storeItemId;
        List<String> list = entityManager.createNativeQuery(query).getResultList();

        Map<String, Integer> ratingDistribution;

        try {
            ratingDistribution = new ObjectMapper().readValue(list.get(0), Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Double averageStoreItemRating = storeItemCustomerRatingRepository.countAverageStoreItemRating(storeItemId);

        StoreItemCustomerRatingEntity storeItemCustomerRatingEntity = storeItemCustomerRatingRepository.findAllByCustomerIdAndStoreItemId(customerId, storeItemId);

        StoreItemEntity storeItemEntity = storeItemRepository.findById(storeItemId).get();

        StoreItem storeItem = new StoreItem(
                storeItemEntity.getId(),
                storeItemEntity.getName(),
                storeItemEntity.getPrice(),
                storeItemEntity.getDescription(),
                averageStoreItemRating,
                storeItemCustomerRatingEntity.getRating(),
                ratingDistribution
        );

        StoreItemResponse storeItemResponse = new StoreItemResponse();
        storeItemResponse.setItems(Arrays.asList(storeItem));

        return storeItemResponse;
    }

    public void setCustomerStoreItemRating(Integer customerId, Integer storeItemId, Integer rating) {

        StoreItemCustomerRatingEntity storeItemCustomerRatingEntity = storeItemCustomerRatingRepository.findAllByCustomerIdAndStoreItemId(customerId, storeItemId);

        if(storeItemCustomerRatingEntity != null) {
            storeItemCustomerRatingEntity.setRating(rating);
        } else {
            storeItemCustomerRatingEntity = new StoreItemCustomerRatingEntity(customerId, storeItemId, rating);
        }

        storeItemCustomerRatingRepository.save(storeItemCustomerRatingEntity);
    }

    public BigDecimal getTotalPrice(Integer customerId, Integer storeItemId, BigDecimal amount) {
        return OrderCalculator.calculate(customerRepository.findById(customerId).get(), storeItemRepository.findById(storeItemId).get(), amount);
    }
}
