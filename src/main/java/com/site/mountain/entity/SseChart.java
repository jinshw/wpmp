package com.site.mountain.entity;

public class SseChart {
    private String opttime;
    private int total;
    private String type;
    private int value;
    private String name;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOpttime() {
        return opttime;
    }

    public void setOpttime(String opttime) {
        this.opttime = opttime;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
