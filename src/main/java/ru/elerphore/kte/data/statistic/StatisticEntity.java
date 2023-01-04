package ru.elerphore.kte.data.statistic;

import ru.elerphore.kte.data.customer.CustomerEntity;
import ru.elerphore.kte.data.storeitem.StoreItemEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "statistics")
public class StatisticEntity {
    @Id
    private Integer id;

    @Column
    private StatisticTypeEnum statisticType;

    @OneToOne(mappedBy = "id")
    private StoreItemEntity storeItem;

    @OneToOne(mappedBy = "id")
    private CustomerEntity customer;

    @Column
    private BigDecimal totalPrice;

    @Column
    private BigDecimal discountSum;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public StatisticTypeEnum getStatisticType() {
        return statisticType;
    }

    public void setStatisticType(StatisticTypeEnum statisticType) {
        this.statisticType = statisticType;
    }

    public StoreItemEntity getStoreItem() {
        return storeItem;
    }

    public void setStoreItem(StoreItemEntity storeItem) {
        this.storeItem = storeItem;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}
