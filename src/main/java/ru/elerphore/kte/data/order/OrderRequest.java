package ru.elerphore.kte.data.order;

import ru.elerphore.kte.data.storeitem.StoreItem;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "NewOrderRequest", namespace = "http://kte.assigment.application")
public class OrderRequest {
    private Integer customerId;
    private ArrayList<StoreItem> storeItemList;
    private BigDecimal totalPrice;

//    public OrderRequest() {
//
//    }
//
//    public OrderRequest(ArrayList<StoreItem> storeItemList) {
//        this.storeItemList = storeItemList;
//    }

    @XmlElement(name = "storeitem", namespace = "http://kte.assigment.application")
    public ArrayList<StoreItem> getStoreItemList() {
        return storeItemList;
    }

    public void setStoreItemList(ArrayList<StoreItem> storeItemList) {
        this.storeItemList = storeItemList;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
