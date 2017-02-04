package com.sendmoney.kaffka.sendmoney.net.jobs;

import android.content.Context;

import com.sendmoney.kaffka.sendmoney.Preferences;
import com.sendmoney.kaffka.sendmoney.SendMoneyApplication;
import com.sendmoney.kaffka.sendmoney.models.Transfer;
import com.sendmoney.kaffka.sendmoney.net.NeonTestService;
import com.sendmoney.kaffka.sendmoney.net.callbacks.GenerateTokenCallback;
import com.sendmoney.kaffka.sendmoney.net.callbacks.GetTransfersCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gabri on 04/02/2017.
 */

public class GenerateTokenJob {

    private GenerateTokenCallback generateTokenCallback;
    private Context context;

    public GenerateTokenJob(GenerateTokenCallback generateTokenCallback, Context context) {
        this.generateTokenCallback = generateTokenCallback;
        this.context = context;
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
