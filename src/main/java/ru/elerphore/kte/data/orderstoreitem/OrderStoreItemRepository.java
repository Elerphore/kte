package ru.elerphore.kte.data.orderstoreitem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.elerphore.kte.data.storeitem.StoreItemEntity;

import java.util.List;

@Repository
public interface OrderStoreItemRepository extends JpaRepository<OrderStoreItemEntity, Integer> {
    List<OrderStoreItemEntity> findOrderStoreItemEntitiesByStoreItem(StoreItemEntity storeItem);
}
