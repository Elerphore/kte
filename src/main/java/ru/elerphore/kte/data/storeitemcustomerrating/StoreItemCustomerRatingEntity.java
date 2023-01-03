package ru.elerphore.kte.data.storeitemcustomerrating;

import javax.persistence.*;

@Entity(name = "store_item_customer_rating")
public class StoreItemCustomerRatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @OneToOne
//    @JoinColumn(name = "customer_id")
    @Column
    private Integer customerId;

//    @OneToOne
//    @JoinColumn(name = "storeitem_id")
    @Column
    private Integer storeitemId;

    @Column
    private Double rating;

    public StoreItemCustomerRatingEntity() {

    }

    public StoreItemCustomerRatingEntity(Integer id, Integer customerId, Integer storeItemId, Double rating) {
        this.id = id;
        this.customerId = customerId;
        this.storeitemId = storeItemId;
        this.rating = rating;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getStoreItemId() {
        return storeitemId;
    }

    public void setStoreItemId(Integer storeItemId) {
        this.storeitemId = storeItemId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
