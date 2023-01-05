package ru.elerphore.kte.data.statistic;

import java.util.List;

public class StatisticResponse {
    private List<StatisticItem> items;

    public StatisticResponse() { }

    public StatisticResponse(List<StatisticItem> items) {
        this.items = items;
    }

    public List<StatisticItem> getItems() {
        return items;
    }

    public void setItems(List<StatisticItem> items) {
        this.items = items;
    }
}
