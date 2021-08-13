package com.example.aljadiproject.APIIntegration;

import com.example.aljadiproject.Models.GetAbsentEmployeesReponse;
import com.example.aljadiproject.Models.GetDashboardResponse;
import com.example.aljadiproject.Models.GetOnLeavesResponse;
import com.example.aljadiproject.Models.GetPendingLeavesResponse;
import com.example.aljadiproject.Models.LoginApiData.LoginRequest;
import com.example.aljadiproject.Models.LoginResponse;
import com.example.aljadiproject.Models.GetPresentEmployeesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface apiset {

    @POST("login")
    Call<LoginResponse> verifyUser(@Body LoginRequest loginRequest);


   @Headers("Content-Type: application/json")
    @GET("attendance/present/employees")
    Call<GetPresentEmployeesResponse> getPresentEmployees();

    @Headers("Content-Type: application/json")
    @GET("attendance/absent/employees")
    Call<GetAbsentEmployeesReponse> getAbsentEmployees();

    @Headers("Content-Type: application/json")
    @GET("leaves/pending")
    Call<GetPendingLeavesResponse> getPendingLeaves();

    @Headers("Content-Type: application/json")
    @GET("leaves/employees/on/leave")
    Call<GetOnLeavesResponse> getOnLeaves();

    @Headers("Content-Type: application/json")
    @GET("dashboard")
    Call<GetDashboardResponse> getDashboardData();
}
 