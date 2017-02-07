package com.sendmoney.kaffka.sendmoney.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by kaffka on 04/02/2017.
 */

public class Transfer extends RealmObject {

    @SerializedName("Id")
    private long id;
    @SerializedName("ClienteId")
    private long clientId;
    @SerializedName("Valor")
    private double value;
    @SerializedName("Token")
    private String token;
    @SerializedName("Data")
    private double data;

    public Transfer() {
    }

    public Transfer(long id, long clientId, double value, String token, double data) {
        this.id = id;
        this.clientId = clientId;
        this.value = value;
        this.token = token;
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public long getClientId() {
        return clientId;
    }

    public double getValue() {
        return value;
    }

    public String getToken() {
        return token;
    }

    public double getData() {
        return data;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setData(double data) {
        this.data = data;
    }
}
