package ru.elerphore.kte.data.orderstoreitem;

import ru.elerphore.kte.data.order.OrderEntity;
import ru.elerphore.kte.data.storeitem.StoreItemEntity;

import javax.persistence.*;

@Entity(name = "order_storeitem")
public class OrderStoreItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name = "storeitem_id")
    private StoreItemEntity storeItemEntity;

    @Column
    private Integer amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public StoreItemEntity getStoreItemEntity() {
        return storeItemEntity;
    }

    public void setStoreItemEntity(StoreItemEntity storeItemEntity) {
        this.storeItemEntity = storeItemEntity;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
