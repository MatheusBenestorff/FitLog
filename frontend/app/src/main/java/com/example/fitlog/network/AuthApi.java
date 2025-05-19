package com.example.fitlog.network;

import com.example.fitlog.network.requests.LoginRequest;
import com.example.fitlog.network.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest request);
}
