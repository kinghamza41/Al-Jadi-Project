package com.example.aljadiproject.Models;

import com.example.aljadiproject.Models.DashboardApiData.DashboardActualData;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetDashboardResponse {
      @SerializedName("data")
     DashboardActualData dashboardActualData;

    public GetDashboardResponse() {
    }


    public DashboardActualData getDashboardActualData() {
        return dashboardActualData;
    }

    public void setDashboardActualData(DashboardActualData dashboardActualData) {
        this.dashboardActualData = dashboardActualData;
    }
}
