package ru.elerphore.kte.data.order;

import ru.elerphore.kte.data.storeitem.StoreItem;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;
import java.util.ArrayList;

@XmlType(name = "NewOrderRequest", namespace = "http://kte.assigment.application")
public class OrderRequest {
    private Integer customerId;
    private ArrayList<StoreItem> storeItemList;
    private BigDecimal totalPrice;

    @XmlElement(name = "storeitem", namespace = "http://kte.assigment.application")
    public ArrayList<StoreItem> getStoreItemList() {
        return storeItemList;
    }

    public void setStoreItemList(ArrayList<StoreItem> storeItemList) {
        this.storeItemList = storeItemList;
    }

    @XmlElement(name = "customerId", namespace = "http://kte.assigment.application")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @XmlElement(name = "totalPrice", namespace = "http://kte.assigment.application")
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
