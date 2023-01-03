package ru.elerphore.kte.data.storeitem;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreItemRepository extends CrudRepository<StoreItemEntity, Long> {
    @Override
    List<StoreItemEntity> findAll();
}
