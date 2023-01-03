package ru.elerphore.kte.data.storeitemcustomerrating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreItemCustomerRatingRepository extends JpaRepository<StoreItemCustomerRatingEntity, Integer> {
    // оно не округляем, чекни, позже, пж.
    @Query
    (
        "select AVG(rating) as averagerating from store_item_customer_rating where storeitem_id = :storeItemId"
    )
    Double countAverageStoreItemRating(Integer storeItemId);

    @Query("select new ru.elerphore.kte.data.storeitemcustomerrating.StoreItemCustomerRatingEntity(id, customerId, storeitemId, rating) from store_item_customer_rating where customerId = :customerId and storeitemId = :storeItemId")
    StoreItemCustomerRatingEntity findAllByCustomerIdAndStoreItemId(Integer customerId, Integer storeItemId);
}
