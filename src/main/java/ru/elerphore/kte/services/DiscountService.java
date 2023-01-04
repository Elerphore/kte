package ru.elerphore.kte.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.elerphore.kte.data.discount.DiscountRepository;
import ru.elerphore.kte.data.storeitem.StoreItemEntity;
import ru.elerphore.kte.data.storeitem.StoreItemRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class DiscountService {
    private DiscountRepository discountRepository;
    private StoreItemRepository storeItemRepository;
    private StoreItemEntity lastStoreItemEntity = null;
    private EntityManager entityManager;

    @Autowired
    public DiscountService(DiscountRepository discountRepository, StoreItemRepository storeItemRepository, EntityManager entityManager) {
        this.discountRepository = discountRepository;
        this.storeItemRepository = storeItemRepository;
        this.entityManager = entityManager;
    }

    @Scheduled(fixedRate = 100)
    void discountStoreItemSelection() {
        if(lastStoreItemEntity != null) {
            lastStoreItemEntity.setDiscount(null);
            storeItemRepository.save(lastStoreItemEntity);
        }

        StoreItemEntity storeItemEntityList = storeItemRepository.findAllOrderByRandom().get(0);

    }
}