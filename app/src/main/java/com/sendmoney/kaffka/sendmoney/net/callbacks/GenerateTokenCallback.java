package com.sendmoney.kaffka.sendmoney.net.callbacks;

/**
 * Created by kaffka on 04/02/2017.
 */

public interface GenerateTokenCallback {
    void onTokenGenerateSuccess(String token);
    void onTokenGenerateError();
}
