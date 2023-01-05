package ru.elerphore.kte.data.customer;

import javax.xml.bind.annotation.XmlElement;

public class CustomerRequest {
    private Integer customerId;
    private Integer discountOneId;
    private Integer discountTwoId;

    @XmlElement(name = "customerId", namespace = "http://kte.assigment.application")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @XmlElement(name = "discountOne", namespace = "http://kte.assigment.application")
    public Integer getDiscountOneId() {
        return discountOneId;
    }

    public void setDiscountOneId(Integer discountOneId) {
        this.discountOneId = discountOneId;
    }

    @XmlElement(name = "discountTwo", namespace = "http://kte.assigment.application")
    public Integer getDiscountTwoId() {
        return discountTwoId;
    }

    public void setDiscountTwoId(Integer discountTwoId) {
        this.discountTwoId = discountTwoId;
    }
}
