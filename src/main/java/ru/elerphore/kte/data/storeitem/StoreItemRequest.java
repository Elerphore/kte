package ru.elerphore.kte.data.storeitem;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;

@XmlType(name = "NewStoreItemRequest", namespace = "http://kte.assigment.application")
public class StoreItemRequest {
    private Integer customerId;
    private Integer storeItemId;
    private Integer rating;
    private BigDecimal amount;

    @XmlElement(name = "customerId", namespace = "http://kte.assigment.application")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @XmlElement(name = "storeItemId", namespace = "http://kte.assigment.application")
    public Integer getStoreItemId() {
        return storeItemId;
    }

    public void setStoreItemId(Integer storeItemId) {
        this.storeItemId = storeItemId;
    }

    @XmlElement(required = false, name = "rating", namespace = "http://kte.assigment.application")
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @XmlElement(required = false, name = "amount", namespace = "http://kte.assigment.application")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
