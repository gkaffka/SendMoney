package com.sendmoney.kaffka.sendmoney.net.jobs;

import android.content.Context;

import com.sendmoney.kaffka.sendmoney.Preferences;
import com.sendmoney.kaffka.sendmoney.SendMoneyApplication;
import com.sendmoney.kaffka.sendmoney.models.Transfer;
import com.sendmoney.kaffka.sendmoney.net.NeonTestService;
import com.sendmoney.kaffka.sendmoney.net.callbacks.GetTransfersCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kaffka on 04/02/2017.
 */

public class GetTransfersJob {

    private final GetTransfersCallback getTransfersCallback;
    private final Context context;

    public GetTransfersJob(GetTransfersCallback getTransfersCallback, Context context) {
        this.getTransfersCallback = getTransfersCallback;
        this.context = context;
    }

    public void getTransfers() {
        String token = new Preferences(context).getToken();
        SendMoneyApplication.getInstance().getRetrofitClient().create(NeonTestService.class).getTransfers(token).enqueue(new Callback<List<Transfer>>() {
            @Override
            public void onResponse(Call<List<Transfer>> call, Response<List<Transfer>> response) {
                if (response.isSuccessful()) {
                    getTransfersCallback.onGetTransfersSuccess(response.body());
                    return;
                }
                getTransfersCallback.onGetTransfersError();
            }

            @Override
            public void onFailure(Call<List<Transfer>> call, Throwable t) {
                getTransfersCallback.onGetTransfersError();
            }
        });
    }
}
