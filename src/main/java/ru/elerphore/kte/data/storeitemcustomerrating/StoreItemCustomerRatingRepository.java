package ru.elerphore.kte.data.storeitemcustomerrating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Query(value = "select cast(json_object_agg(rating, amount) as text) from (select distinct count(rating) as amount from store_item_customer_rating where storeitem_id = 1 group by rating) as ratingAmount, store_item_customer_rating where storeitem_id = :storeItemId", nativeQuery = true)
    List<String> findAllRatingDistribution(Integer storeItemId);

}
