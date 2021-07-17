package com.example.aljadiproject.APIIntegration;

import com.example.aljadiproject.Models.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface apiset {

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseModel> verifyuser(
            @Field("email") String Email,
            @Field("password") String Password
    );
}
