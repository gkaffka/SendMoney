package com.sendmoney.kaffka.sendmoney;

import android.app.Application;
import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kaffka on 04/02/2017.
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
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @NonNull
    private OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        return client;
    }

    public Retrofit getRetrofitClient() {
        if (defaultRetrofit == null) {
            defaultRetrofit = configureRetrofit(BASE_URL);
        }
        return defaultRetrofit;
    }

}
