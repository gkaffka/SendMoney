package com.sendmoney.kaffka.sendmoney;

import android.app.Application;
import android.support.annotation.NonNull;

import com.sendmoney.kaffka.sendmoney.models.Contact;

import java.util.ArrayList;
import java.util.List;
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
    private static final String BASE_URL = "http://processoseletivoneon.azurewebsites.net/";
    private Retrofit defaultRetrofit;
    private List<Contact> contactList;

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
        initContactList();
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
        return new OkHttpClient.Builder().addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    public Retrofit getRetrofitClient() {
        if (defaultRetrofit == null) {
            defaultRetrofit = configureRetrofit(BASE_URL);
        }
        return defaultRetrofit;
    }

    public List<Contact> getContacts() {
        return contactList;
    }

    private void initContactList() {
        contactList = new ArrayList<>();
        contactList.add(new Contact("Morpheus Pimpão", 1));
        contactList.add(new Contact("Trinity Cançada", 2));
        contactList.add(new Contact("The Oracle", 3));
        contactList.add(new Contact("Agent Smith", 4));
        contactList.add(new Contact("Mr. Anderson", 5));
        contactList.add(new Contact("Agent Jones", 6));
        contactList.add(new Contact("Cypher Safadão", 7));
        contactList.add(new Contact("Spoon Boy", 8));
        contactList.add(new Contact("Tank Sumido", 9));
        contactList.add(new Contact("Dozer Danado", 10));
        contactList.add(new Contact("The One", 11));
        contactList.add(new Contact("The Two", 12));
        contactList.add(new Contact("The The", 13));
        contactList.add(new Contact("Dujour Dujour", 14));
        contactList.add(new Contact("Mouse Mickey", 15));
        contactList.add(new Contact("Finn Thehumnan", 16));
        contactList.add(new Contact("Jake Thedog", 17));
        contactList.add(new Contact("Mr. Bombastic", 18));
    }

    public void resetContactsTotals() {
        initContactList();
    }
}
