package ru.elerphore.kte.data.customer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(name = "CustomerResponse", namespace = "http://kte.assigment.application")
public class CustomerResponse {
    protected List<CustomerEntity> items;

    @XmlElement(required = true, name="items")
    public List<CustomerEntity> getItems() {
        return items;
    }

    public void setItems(List<CustomerEntity> items) {
        this.items = items;
    }
}
