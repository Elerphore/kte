package ru.elerphore.kte.data.storeitem;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(name = "StoreItemResponse", namespace = "http://kte.assigment.application")
public class StoreItemResponse {
    private List<StoreItem> items;

    public StoreItemResponse() { }

    public StoreItemResponse(List<StoreItem> items) {
        this.items = items;
    }

    @XmlElement(required = true, name="items")
    public List<StoreItem> getItems() {
        return items;
    }

    public void setItems(List<StoreItem> items) {
        this.items = items;
    }
}
