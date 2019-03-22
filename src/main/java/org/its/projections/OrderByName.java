package org.its.projections;

import org.its.bus.BusMessage;

public class OrderByName implements BusMessage {
    private String name;
    private long count;

    public OrderByName(){

    }

    public OrderByName(String name, long count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
