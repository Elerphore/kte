package ru.elerphore.kte.data.statistic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.elerphore.kte.data.customer.CustomerEntity;
import ru.elerphore.kte.data.storeitem.StoreItemEntity;

@Repository
public interface StatisticsRepository extends JpaRepository<StatisticEntity, Integer> {
    StatisticEntity findStatisticEntitiesByStoreItem(StoreItemEntity storeItem);
    StatisticEntity findStatisticEntitiesByCustomer(CustomerEntity customer);

    StatisticEntity findStatisticEntitiesByCustomerId(Integer customerId);
    StatisticEntity findStatisticEntitiesByStoreItemId(Integer storeItemId);
}
