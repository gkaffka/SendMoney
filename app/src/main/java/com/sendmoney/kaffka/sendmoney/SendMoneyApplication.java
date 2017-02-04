package com.sendmoney.kaffka.sendmoney;

import android.app.Application;
import android.support.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gabri on 04/02/2017.
 */

public class SendMoneyApplication extends Application {

    private static SendMoneyApplication instance;
    public static final String BASE_URL = "http://processoseletivoneon.azurewebsites.net/";
    private Retrofit defaultRetrofit;


    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }

    public static SendMoneyApplication getInstance() {
        return instance;
    }

    @NonNull
    private Retrofit configureRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofitClient() {
        if (defaultRetrofit == null) {
            defaultRetrofit = configureRetrofit(BASE_URL);
        }
        return defaultRetrofit;
    }

}
