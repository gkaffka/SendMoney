package com.sendmoney.kaffka.sendmoney.models;

/**
 * Created by kaffka on 04/02/2017.
 */

public class Contact {

    private String name;
    private String id;
    private String avatarUrl;
    private double amountReceived;

    public Contact() {
    }

    public Contact(String name, String id, String avatarUrl, double amountReceived) {
        this.name = name;
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.amountReceived = amountReceived;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public double getAmountReceived() {
        return amountReceived;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setAmountReceived(double amountReceived) {
        this.amountReceived = amountReceived;
    }
}
