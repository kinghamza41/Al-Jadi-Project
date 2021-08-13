package com.example.aljadiproject.Models;

import com.example.aljadiproject.Models.AbsentEmployeeApiData.AbsentData;
import com.example.aljadiproject.Models.OnLeavesApiData.OnLeavesData;
import com.google.gson.annotations.SerializedName;

public class GetOnLeavesResponse {
    @SerializedName("data")
    private OnLeavesData data;

    public GetOnLeavesResponse(OnLeavesData data) {
        this.data = data;
    }

    public OnLeavesData getData() {
        return data;
    }

    public void setData(OnLeavesData data) {
        this.data = data;
    }
}
