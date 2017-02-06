package com.sendmoney.kaffka.sendmoney.net.jobs;

import android.content.Context;

import com.sendmoney.kaffka.sendmoney.Preferences;
import com.sendmoney.kaffka.sendmoney.SendMoneyApplication;
import com.sendmoney.kaffka.sendmoney.net.NeonTestService;
import com.sendmoney.kaffka.sendmoney.net.callbacks.SendMoneyCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kaffka on 04/02/2017.
 */

public class SendMoneyJob {

    private SendMoneyCallback sendMoneyCallback;
    private Context context;

    public SendMoneyJob(SendMoneyCallback sendMoneyCallback, Context context) {
        this.sendMoneyCallback = sendMoneyCallback;
        this.context = context;
    }

    public void sendMoney(String clientId, double value) {
        String token = new Preferences(context).getToken();
        SendMoneyApplication.getInstance().getRetrofitClient().create(NeonTestService.class).sendMoney(clientId, token, value).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    sendMoneyCallback.onSendMoneySuccess();
                    return;
                }
                sendMoneyCallback.onSendMoneyError();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                sendMoneyCallback.onSendMoneyError();
            }
        });
    }
}
