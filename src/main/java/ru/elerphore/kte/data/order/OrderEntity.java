package ru.elerphore.kte.data.order;

import ru.elerphore.kte.data.customer.CustomerEntity;
import ru.elerphore.kte.data.orderstoreitem.OrderStoreItemEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;

    @Column
    private LocalDateTime createDate = LocalDateTime.now();

    @Column
    private BigDecimal discountSum;

    @Column
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderStoreItemEntity> orderStoreItemEntityList;

    @Column
    private String orderNumber;

    public OrderEntity() {

    }

    public OrderEntity(CustomerEntity customerEntity, String orderNumber, List<OrderStoreItemEntity> orderStoreItemEntityList, BigDecimal discountSum, BigDecimal totalPrice) {
        this.customerEntity = customerEntity;
        this.orderNumber = orderNumber;
        this.orderStoreItemEntityList = orderStoreItemEntityList;
        this.discountSum = discountSum;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<OrderStoreItemEntity> getOrderStoreItemEntityList() {
        return orderStoreItemEntityList;
    }

    public void setOrderStoreItemEntityList(List<OrderStoreItemEntity> orderStoreItemEntityList) {
        this.orderStoreItemEntityList = orderStoreItemEntityList;
    }

    public BigDecimal getDiscountSum() {
        return discountSum;
    }

    public void setDiscountSum(BigDecimal discountSum) {
        this.discountSum = discountSum;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
