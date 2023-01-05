package ru.elerphore.kte.services.discount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.elerphore.kte.data.discount.DiscountEntity;
import ru.elerphore.kte.data.discount.DiscountRepository;
import ru.elerphore.kte.data.storeitem.StoreItemEntity;
import ru.elerphore.kte.data.storeitem.StoreItemRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
public class DiscountService {
    private DiscountRepository discountRepository;
    private StoreItemRepository storeItemRepository;
    private StoreItemEntity lastStoreItemEntity = null;
    private EntityManager entityManager;
    private Random random = new Random();

    private final Integer discountId = 1;

    @Autowired
    public DiscountService(DiscountRepository discountRepository, StoreItemRepository storeItemRepository, EntityManager entityManager) {
        this.discountRepository = discountRepository;
        this.storeItemRepository = storeItemRepository;
        this.entityManager = entityManager;
    }

    @Scheduled(fixedRate = 360000)
    void discountStoreItemSelection() {
        if(lastStoreItemEntity != null) {
            lastStoreItemEntity.setDiscount(null);
            storeItemRepository.save(lastStoreItemEntity);
        }

        List<StoreItemEntity> storeItemEntityList = storeItemRepository.findAllByDiscountNull();

        if(storeItemEntityList.size() > 0) {
            DiscountEntity discountEntity = discountRepository.findById(discountId).get();

            StoreItemEntity storeItemEntity = storeItemEntityList
                    .get(random.ints(0, storeItemEntityList.size()).findFirst().getAsInt());

            storeItemEntity.setDiscount(discountEntity);

            lastStoreItemEntity = storeItemRepository.save(storeItemEntity);
        }
    }
}
