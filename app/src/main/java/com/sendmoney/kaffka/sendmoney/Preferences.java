package com.sendmoney.kaffka.sendmoney;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by gabri on 04/02/2017.
 */

public class Preferences {

    private static final String PREFERENCE = "com.sendmoney.kaffka.sendmoney";
    private static final String TOKEN = "com.sendmoney.kaffka.sendmoney.token";

    private final SharedPreferences preference;
    private final Gson gson;

    public Preferences(Context context) {
        this.preference = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        this.gson = new Gson();
    }

    public String getToken() {
        return preference.getString(TOKEN, null);
    }

    public void setToken(String token) {
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }
}
