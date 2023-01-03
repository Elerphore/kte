package ru.elerphore.kte.data.storeitem;

import java.math.BigDecimal;
import java.util.Map;

public class StoreItem {
    public Integer id;
    public String name;
    public BigDecimal price;

    public String description;

    public Double averageRating;
    public Double customerRating;
    public Map<String, Integer> ratingDistribution;

    public StoreItem(Integer id, String name, BigDecimal price) {
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
            Double customerRating,
            Map<String, Integer> ratingDistribution) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.averageRating = averageRating;
        this.customerRating = customerRating;
        this.ratingDistribution = ratingDistribution;

    }
}
