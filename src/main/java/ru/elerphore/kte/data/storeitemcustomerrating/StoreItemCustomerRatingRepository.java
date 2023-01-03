package ru.elerphore.kte.data.storeitemcustomerrating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.elerphore.kte.data.customer.CustomerEntity;
import ru.elerphore.kte.data.storeitem.StoreItemEntity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public interface StoreItemCustomerRatingRepository extends JpaRepository<StoreItemCustomerRatingEntity, Long> {
    // оно не округляем, чекни, позже, пж.
    @Query
    (
        "select AVG(rating) as averagerating from store_item_customer_rating where storeitem_id = :storeItemId"
    )
    Long countAverageStoreItemRating(Long storeItemId);

    List<StoreItemCustomerRatingEntity> findAllByStoreItemEntity(StoreItemEntity storeItemEntity);

    StoreItemCustomerRatingEntity findByCustomerEntityAndStoreItemEntity(CustomerEntity customerEntity, StoreItemEntity storeItemEntity);
}
