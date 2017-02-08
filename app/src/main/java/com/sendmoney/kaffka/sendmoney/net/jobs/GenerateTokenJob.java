package com.sendmoney.kaffka.sendmoney.net.jobs;

import com.sendmoney.kaffka.sendmoney.SendMoneyApplication;
import com.sendmoney.kaffka.sendmoney.net.NeonTestService;
import com.sendmoney.kaffka.sendmoney.net.callbacks.GenerateTokenCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kaffka on 04/02/2017.
 */

public class GenerateTokenJob {

    private final GenerateTokenCallback generateTokenCallback;

    public GenerateTokenJob(GenerateTokenCallback generateTokenCallback) {
        this.generateTokenCallback = generateTokenCallback;
    }

    public void generateToken(String name, String email) {
        SendMoneyApplication.getInstance().getRetrofitClient().create(NeonTestService.class).getToken(name, email).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    generateTokenCallback.onTokenGenerateSuccess(response.body());
                    return;
                }
                generateTokenCallback.onTokenGenerateError();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                generateTokenCallback.onTokenGenerateError();
            }
        });
    }
}
