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

    private StatisticsRepository statisticsRepository;
    private CustomerRepository customerRepository;
    private StoreItemRepository storeItemRepository;
    private OrderStoreItemRepository orderStoreItemRepository;
    private OrderRepository orderRepository;

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

        StatisticEntity statisticEntity = new StatisticEntity();
        statisticEntity.setStoreItem(storeItemEntity);
        statisticEntity.setDiscountSum(totalDiscountSum);
        statisticEntity.setTotalPrice(totalPrice);

        statisticEntity.setStatisticType(StatisticTypeEnum.STOREITEM);

        statisticsRepository.save(statisticEntity);
    }

    private void generateCustomerStatistics(CustomerEntity customerEntity) {
        BigDecimal totalDiscountSum = orderRepository.findAllByCustomerEntity(customerEntity).stream().map(OrderEntity::getDiscountSum).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalPrice = orderRepository.findAllByCustomerEntity(customerEntity).stream().map(OrderEntity::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        StatisticEntity statisticEntity = new StatisticEntity();
        statisticEntity.setStatisticType(StatisticTypeEnum.STOREITEM);
        statisticEntity.setCustomer(customerEntity);
        statisticEntity.setDiscountSum(totalDiscountSum);
        statisticEntity.setTotalPrice(totalPrice);

        statisticEntity.setCustomer(customerEntity);

        statisticsRepository.save(statisticEntity);
    }
}
