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
import ru.elerphore.kte.data.statistic.*;
import ru.elerphore.kte.data.storeitem.StoreItemEntity;
import ru.elerphore.kte.data.storeitem.StoreItemRepository;

import java.math.BigDecimal;
import java.util.Collections;
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

    /**
     * генерация статистики и скидок производится фоновым процессом по таймеру;
    * */

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
        statisticEntity.setOrdersAmount(orderStoreItemEntityList.size());

        statisticEntity.setStatisticType(StatisticTypeEnum.STOREITEM);
        statisticEntity.setStoreItem(storeItemEntity);

        statisticsRepository.save(statisticEntity);
    }

    private void generateCustomerStatistics(CustomerEntity customerEntity) {
        List<OrderEntity> orders = orderRepository.findAllByCustomerEntity(customerEntity);

        BigDecimal totalDiscountSum = orders.stream().map(OrderEntity::getDiscountSum).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalPrice = orders.stream().map(OrderEntity::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        StatisticEntity statisticEntity = statisticsRepository.findStatisticEntitiesByCustomer(customerEntity);

        if(statisticEntity == null) {
            statisticEntity = new StatisticEntity();
        }

        statisticEntity.setDiscountSum(totalDiscountSum);
        statisticEntity.setTotalPrice(totalPrice);
        statisticEntity.setOrdersAmount(orders.size());

        statisticEntity.setStatisticType(StatisticTypeEnum.CUSTOMER);
        statisticEntity.setCustomer(customerEntity);

        statisticsRepository.save(statisticEntity);
    }

    public StatisticResponse getStatistic(StatisticRequest statisticRequest) throws OnlyOneIdentifierAvailableException {
        if(statisticRequest.getStoreItemId() != null && statisticRequest.getStoreItemId() != null) {
            throw new OnlyOneIdentifierAvailableException();
        }

        StatisticEntity statisticEntity = null;

        if(statisticRequest.getCustomerId() != null) {
            statisticEntity = statisticsRepository.findStatisticEntitiesByCustomerId(statisticRequest.getCustomerId());
        }

        if(statisticRequest.getStoreItemId() != null) {
            statisticEntity = statisticsRepository.findStatisticEntitiesByStoreItemId(statisticRequest.getStoreItemId());
        }

        return new StatisticResponse(Collections.singletonList(new StatisticItem(statisticEntity)));

    }
}
