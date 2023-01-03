package ru.elerphore.kte.data.storeitem;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(name = "StoreItemResponse", namespace = "http://kte.assigment.application")
public class StoreItemResponse {
    private List<StoreItemEntity> items;

    @XmlElement(required = true, name="items")
    public List<StoreItemEntity> getItems() {
        return items;
    }

    public void setItems(List<StoreItemEntity> items) {
        this.items = items;
    }
}
