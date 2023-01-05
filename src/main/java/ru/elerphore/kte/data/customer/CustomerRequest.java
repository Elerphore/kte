package ru.elerphore.kte.data.customer;

public class CustomerRequest {
    private Integer customerId;
    private Integer discountOneId;
    private Integer discountTwoId;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getDiscountOneId() {
        return discountOneId;
    }

    public void setDiscountOneId(Integer discountOneId) {
        this.discountOneId = discountOneId;
    }

    public Integer getDiscountTwoId() {
        return discountTwoId;
    }

    public void setDiscountTwoId(Integer discountTwoId) {
        this.discountTwoId = discountTwoId;
    }
}
