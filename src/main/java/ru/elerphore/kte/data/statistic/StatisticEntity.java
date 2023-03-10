package ru.elerphore.kte.data.statistic;

import ru.elerphore.kte.data.customer.CustomerEntity;
import ru.elerphore.kte.data.storeitem.StoreItemEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "statistics")
public class StatisticEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private StatisticTypeEnum statisticType;

    @ManyToOne
    @JoinColumn(name = "storeitem_id")
    private StoreItemEntity storeItem;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @Column
    private BigDecimal totalPrice;

    @Column
    private BigDecimal discountSum;

    @Column
    private Integer ordersAmount;

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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getDiscountSum() {
        return discountSum;
    }

    public void setDiscountSum(BigDecimal discountSum) {
        this.discountSum = discountSum;
    }

    public Integer getOrdersAmount() {
        return ordersAmount;
    }

    public void setOrdersAmount(Integer ordersAmount) {
        this.ordersAmount = ordersAmount;
    }
}
