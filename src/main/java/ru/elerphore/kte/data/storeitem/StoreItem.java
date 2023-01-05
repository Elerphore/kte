package ru.elerphore.kte.data.storeitem;

import org.springframework.lang.NonNull;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;
import java.util.Map;

@XmlType(name = "StoreItem", namespace = "http://kte.assigment.application")
public class StoreItem {
    private Integer id;
    private String name;
    private BigDecimal price;

    private BigDecimal amount;

    private String description;

    private Double averageRating;
    private Integer customerRating;
    private Map<String, Integer> ratingDistribution;

    public StoreItem() {
    }

    public StoreItem(Integer id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }

    public StoreItem(Integer id, String name, @NonNull BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public StoreItem(
            Integer id,
            String name,
            BigDecimal price,
            String description,
            Double averageRating,
            Integer customerRating,
            Map<String, Integer> ratingDistribution) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.averageRating = averageRating;
        this.customerRating = customerRating;
        this.ratingDistribution = ratingDistribution;

    }

    @XmlElement(required = true, namespace = "http://kte.assigment.application")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @XmlElement(required = true, namespace = "http://kte.assigment.application")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement(required = false, namespace = "http://kte.assigment.application")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(required = false, namespace = "http://kte.assigment.application")
    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    @XmlElement(required = false, namespace = "http://kte.assigment.application")
    public Integer getCustomerRating() {
        return customerRating;
    }

    public void setCustomerRating(Integer customerRating) {
        this.customerRating = customerRating;
    }

    @XmlElement(required = false, namespace = "http://kte.assigment.application")
    public Map<String, Integer> getRatingDistribution() {
        return ratingDistribution;
    }

    public void setRatingDistribution(Map<String, Integer> ratingDistribution) {
        this.ratingDistribution = ratingDistribution;
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
}
