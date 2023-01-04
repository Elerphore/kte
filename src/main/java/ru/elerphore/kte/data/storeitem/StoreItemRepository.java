package ru.elerphore.kte.data.storeitem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreItemRepository extends JpaRepository<StoreItemEntity, Integer> {
    @Override
    List<StoreItemEntity> findAll();

    @Query("select new ru.elerphore.kte.data.storeitem.StoreItemEntity(id, name, price) from ru.elerphore.kte.data.storeitem.StoreItemEntity where discount = null order by function('RAND')")
    List<StoreItemEntity> findAllOrderByRandom();
}
