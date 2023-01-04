package ru.elerphore.kte.data.orderstoreitem;

import ru.elerphore.kte.data.order.OrderEntity;
import ru.elerphore.kte.data.storeitem.StoreItemEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "order_storeitem")
public class OrderStoreItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "storeitem_id")
    private StoreItemEntity storeItem;

    @Column
    private BigDecimal amount;

    public OrderStoreItemEntity(StoreItemEntity storeItem, BigDecimal amount) {
        this.storeItem = storeItem;
        this.amount = amount;
    }

    public OrderStoreItemEntity() {

    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity orderEntity) {
        this.order = orderEntity;
    }

    public StoreItemEntity getStoreItem() {
        return storeItem;
    }

    public void setStoreItem(StoreItemEntity storeItemEntity) {
        this.storeItem = storeItemEntity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
