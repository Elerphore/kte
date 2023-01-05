package ru.elerphore.kte.services.statistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.elerphore.kte.data.customer.CustomerEntity;
import ru.elerphore.kte.data.customer.CustomerRepository;
import ru.elerphore.kte.data.order.OrderEntity;
import ru.elerphore.kte.data.order.OrderRepository;
import ru.elerphore.kte.data.orderstoreitem.OrderStoreItemEntity;
import ru.elerphore.kte.data.orderstoreitem.OrderStoreItemRepository;
import ru.elerphore.kte.data.statistic.StatisticEntity;
import ru.elerphore.kte.data.statistic.StatisticTypeEnum;
import ru.elerphore.kte.data.statistic.StatisticsRepository;
import ru.elerphore.kte.data.storeitem.StoreItemEntity;
import ru.elerphore.kte.data.storeitem.StoreItemRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StatisticService {

    private final StatisticsRepository statisticsRepository;
    private final CustomerRepository customerRepository;
    private final StoreItemRepository storeItemRepository;
    private final OrderStoreItemRepository orderStoreItemRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public StatisticService(StatisticsRepository statisticsRepository, CustomerRepository customerRepository, StoreItemRepository storeItemRepository, OrderStoreItemRepository orderStoreItemRepository, OrderRepository orderRepository) {
        this.statisticsRepository = statisticsRepository;
        this.customerRepository = customerRepository;
        this.storeItemRepository = storeItemRepository;
        this.orderStoreItemRepository = orderStoreItemRepository;
        this.orderRepository = orderRepository;
    }

    @Scheduled(fixedRate = 1000)
    void statistics() {
        storeItemRepository.findAll().forEach(this::generateStoreItemStatistics);
        customerRepository.findAll().forEach(this::generateCustomerStatistics);
    }

    private void generateStoreItemStatistics(StoreItemEntity storeItemEntity) {
        List<OrderStoreItemEntity> orderStoreItemEntityList = orderStoreItemRepository.findOrderStoreItemEntitiesByStoreItem(storeItemEntity);

        BigDecimal totalDiscountSum = orderStoreItemEntityList.stream().map(OrderStoreItemEntity::getDiscount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalPrice = orderStoreItemEntityList.stream().map(OrderStoreItemEntity::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        StatisticEntity statisticEntity = statisticsRepository.findStatisticEntitiesByStoreItem(storeItemEntity);

        if(statisticEntity == null) {
            statisticEntity = new StatisticEntity();
        }

        statisticEntity.setDiscountSum(totalDiscountSum);
        statisticEntity.setTotalPrice(totalPrice);

        statisticEntity.setStatisticType(StatisticTypeEnum.STOREITEM);
        statisticEntity.setStoreItem(storeItemEntity);

        statisticsRepository.save(statisticEntity);
    }

    private void generateCustomerStatistics(CustomerEntity customerEntity) {
        BigDecimal totalDiscountSum = orderRepository.findAllByCustomerEntity(customerEntity).stream().map(OrderEntity::getDiscountSum).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalPrice = orderRepository.findAllByCustomerEntity(customerEntity).stream().map(OrderEntity::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        StatisticEntity statisticEntity = statisticsRepository.findStatisticEntitiesByCustomer(customerEntity);

        if(statisticEntity == null) {
            statisticEntity = new StatisticEntity();
        }

        statisticEntity.setDiscountSum(totalDiscountSum);
        statisticEntity.setTotalPrice(totalPrice);

        statisticEntity.setStatisticType(StatisticTypeEnum.STOREITEM);
        statisticEntity.setCustomer(customerEntity);

        statisticsRepository.save(statisticEntity);
    }
}
