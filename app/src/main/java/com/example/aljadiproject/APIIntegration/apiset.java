package com.example.aljadiproject.APIIntegration;

import com.example.aljadiproject.Models.LoginApiData.LoginRequest;
import com.example.aljadiproject.Models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface apiset {

    @POST("login")

    Call<LoginResponse> verifyUser(@Body LoginRequest loginRequest)  ;
}
