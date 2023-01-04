package ru.elerphore.kte.data.order;

import ru.elerphore.kte.data.storeitem.StoreItem;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "NewOrderRequest", namespace = "http://kte.assigment.application")
public class OrderRequest {
    private ArrayList<StoreItem> storeItemList;

    public OrderRequest() {

    }

    public OrderRequest(ArrayList<StoreItem> storeItemList) {
        this.storeItemList = storeItemList;
    }

    @XmlElement(name = "storeitem", namespace = "http://kte.assigment.application")
    public ArrayList<StoreItem> getStoreItemList() {
        return storeItemList;
    }

    public void setStoreItemList(ArrayList<StoreItem> storeItemList) {
        this.storeItemList = storeItemList;
    }
}
