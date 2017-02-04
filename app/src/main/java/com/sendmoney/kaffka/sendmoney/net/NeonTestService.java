package com.sendmoney.kaffka.sendmoney.net;

import com.sendmoney.kaffka.sendmoney.models.Transfer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by gabri on 04/02/2017.
 */

public interface NeonTestService {

    @GET("/GenerateToken")
    Call<String> getToken(@Query("name") String name, @Query("email") String email);

    @FormUrlEncoded
    @POST
    Call<Boolean> sendMoney(@Field("ClienteId") String clientId, @Field("token") String token, @Field("valor") double value);

    @GET
    Call<List<Transfer>> getTransfers(@Query("token") String token);
}