package ru.elerphore.kte.data.statistic;

public class StatisticRequest {
    private Integer customerId, storeItemId;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getStoreItemId() {
        return storeItemId;
    }

    public void setStoreItemId(Integer storeItemId) {
        this.storeItemId = storeItemId;
    }
}
