package com.sendmoney.kaffka.sendmoney.models;

/**
 * Created by kaffka on 04/02/2017.
 */

public class Contact {

    private String name;
    private long id;
    private double total;

    public Contact() {
    }

    public Contact(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
