package ru.elerphore.kte.data.storeitem;

import ru.elerphore.kte.data.orderstoreitem.OrderStoreItemEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "storeitems")
public class StoreItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "storeitem_name")
    private String name;

    @Column
    private BigDecimal price;

    @Column
    private String description;

    @Column(name = "average_rating")
    private Double averageRating;

    @OneToMany(mappedBy = "storeItem")
    private List<OrderStoreItemEntity> orderStoreItemEntityList;

    @Transient
    private BigDecimal amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
