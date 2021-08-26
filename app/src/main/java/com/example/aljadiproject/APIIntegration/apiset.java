package com.example.aljadiproject.APIIntegration;

import com.example.aljadiproject.Adapter.AbsentAdapter;
import com.example.aljadiproject.Models.GetAbsentEmployeesReponse;
import com.example.aljadiproject.Models.GetDashboardResponse;
import com.example.aljadiproject.Models.GetOnLeavesResponse;
import com.example.aljadiproject.Models.GetPendingLeavesResponse;
import com.example.aljadiproject.Models.LoginApiData.LoginRequest;
import com.example.aljadiproject.Models.LoginResponse;
import com.example.aljadiproject.Models.GetPresentEmployeesResponse;
import com.example.aljadiproject.Models.PendingLeavesApiData.PendingLeavesActualData;
import com.example.aljadiproject.Models.PendingLeavesApiData.UpdatePendingResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface apiset {

    @POST("login")
    Call<LoginResponse> verifyUser(@Body LoginRequest loginRequest);


    @Headers("Content-Type: application/json")
    @GET("attendance/present/employees")
    Call<GetPresentEmployeesResponse> getPresentEmployees();

    @Headers("Content-Type: application/json")
    @GET("attendance/absent/employees")
    Call<GetAbsentEmployeesReponse> getAbsentEmployees(@Query("page") Integer page);

    @Headers("Content-Type: application/json")
    @GET("leaves/pending")
    Call<GetPendingLeavesResponse> getPendingLeaves(
            @Query("page") Integer page
    );

    @Headers("Content-Type: application/json")

    @PUT("leaves/employee/leave/{id}/approve")
    Call<UpdatePendingResponse> sendLeavesId (
            @Path("id") int id, @Body UpdatePendingResponse updatePendingResponse
    );
    @Headers("Content-Type: application/json")
    @PUT("leaves/employee/leave/{id}/reject")
    Call<UpdatePendingResponse> sendLeavesIdForRejection (
            @Path("id") int id, @Query("reason_for_rejection") String message
            );

    @Headers("Content-Type: application/json")
    @GET("leaves/employees/on/leave")
    Call<GetOnLeavesResponse> getOnLeaves();

    @Headers("Content-Type: application/json")
    @GET("dashboard")
    Call<GetDashboardResponse> getDashboardData();
}
 