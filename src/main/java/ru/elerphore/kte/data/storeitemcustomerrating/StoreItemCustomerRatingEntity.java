package ru.elerphore.kte.data.storeitemcustomerrating;

import ru.elerphore.kte.data.customer.CustomerEntity;
import ru.elerphore.kte.data.storeitem.StoreItemEntity;

import javax.persistence.*;

@Entity(name = "store_item_customer_rating")
public class StoreItemCustomerRatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;

    @OneToOne
    @JoinColumn(name = "storeitem_id")
    private StoreItemEntity storeItemEntity;

    @Column(name = "rating")
    private Double rating;

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public StoreItemEntity getStoreItemEntity() {
        return storeItemEntity;
    }

    public void setStoreItemEntity(StoreItemEntity storeItemEntity) {
        this.storeItemEntity = storeItemEntity;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
