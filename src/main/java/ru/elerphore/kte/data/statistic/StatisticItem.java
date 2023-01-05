package ru.elerphore.kte.data.statistic;

import java.math.BigDecimal;

public class StatisticItem {

    private StatisticTypeEnum statisticType;
    private BigDecimal totalPrice;
    private BigDecimal discountSum;
    private Integer ordersAmount;

    public StatisticItem(StatisticEntity statisticEntity) {
        this.statisticType = statisticEntity.getStatisticType();
        this.totalPrice = statisticEntity.getTotalPrice();
        this.discountSum = statisticEntity.getDiscountSum();
        this.ordersAmount = statisticEntity.getOrdersAmount();
    }

    public StatisticTypeEnum getStatisticType() {
        return statisticType;
    }

    public void setStatisticType(StatisticTypeEnum statisticType) {
        this.statisticType = statisticType;
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
