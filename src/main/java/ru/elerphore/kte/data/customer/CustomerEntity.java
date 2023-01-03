package ru.elerphore.kte.data.customer;

import ru.elerphore.kte.data.discount.DiscountEntity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Entity(name = "customers")
@XmlType(name = "CustomerEntity")
public class CustomerEntity {
    @Id
    private Long id;

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "discount_1")
    private DiscountEntity discountOne;

    @OneToOne
    @JoinColumn(name = "discount_2")
    private DiscountEntity discountTwo;

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(required = true, name="id")
    public Long getId() {
        return id;
    }

    @XmlElement(required = true, name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DiscountEntity getDiscountOne() {
        return discountOne;
    }

    public void setDiscountOne(DiscountEntity discountOne) {
        this.discountOne = discountOne;
    }

    public DiscountEntity getDiscountTwo() {
        return discountTwo;
    }

    public void setDiscountTwo(DiscountEntity discountTwo) {
        this.discountTwo = discountTwo;
    }
}
