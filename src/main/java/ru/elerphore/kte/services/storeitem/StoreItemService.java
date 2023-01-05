package ru.elerphore.kte.services.storeitem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.elerphore.kte.data.customer.CustomerRepository;
import ru.elerphore.kte.data.storeitem.StoreItem;
import ru.elerphore.kte.data.storeitem.StoreItemEntity;
import ru.elerphore.kte.data.storeitem.StoreItemRepository;
import ru.elerphore.kte.data.storeitem.StoreItemResponse;
import ru.elerphore.kte.data.storeitemcustomerrating.StoreItemCustomerRatingEntity;
import ru.elerphore.kte.data.storeitemcustomerrating.StoreItemCustomerRatingRepository;
import ru.elerphore.kte.services.order.OrderCalculator;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StoreItemService {
    private final StoreItemRepository storeItemRepository;
    private final CustomerRepository customerRepository;
    private final StoreItemCustomerRatingRepository storeItemCustomerRatingRepository;

    public StoreItemService(StoreItemRepository storeItemRepository, CustomerRepository customerRepository, StoreItemCustomerRatingRepository storeItemCustomerRatingRepository) {
        this.customerRepository = customerRepository;
        this.storeItemRepository = storeItemRepository;
        this.storeItemCustomerRatingRepository = storeItemCustomerRatingRepository;
    }

    public StoreItemResponse getStoreItems() {
        List<StoreItemEntity> storeItemEntities = storeItemRepository.findAll();
        List<StoreItem> storeItems = storeItemEntities.stream().map(storeItem -> new StoreItem(storeItem.getId(), storeItem.getName(), storeItem.getPrice())).collect(Collectors.toList());

        StoreItemResponse storeItemResponse = new StoreItemResponse();
        storeItemResponse.setItems(storeItems);

        return storeItemResponse;
    }

    public StoreItemResponse getStoreItemDescription(Integer customerId, Integer storeItemId) throws ImpossibleToParseRatingDistributionException {
        Map<String, Integer> ratingDistribution;

        try {
            ratingDistribution = new ObjectMapper().readValue(storeItemCustomerRatingRepository.findAllRatingDistribution(storeItemId).get(0), Map.class);
        } catch (JsonProcessingException e) {
            throw new ImpossibleToParseRatingDistributionException();
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

        return new StoreItemResponse(Collections.singletonList(storeItem));
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

        StoreItemEntity storeItemEntity = storeItemRepository.findById(storeItemId).get();

        BigDecimal discountSum = OrderCalculator.calculateItemDiscountSum(customerRepository.findById(customerId).get(), storeItemEntity, amount);
        BigDecimal totalPrice = OrderCalculator.calculateItemTotalPrice(storeItemEntity, amount);

        return OrderCalculator.calculateItemSumPrice(totalPrice, discountSum);
    }
}
