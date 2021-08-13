package com.example.aljadiproject.Models;

import com.example.aljadiproject.Models.AbsentEmployeeApiData.AbsentData;
import com.example.aljadiproject.Models.PendingLeavesApiData.PendingLeavesData;
import com.google.gson.annotations.SerializedName;

public class GetPendingLeavesResponse {
    @SerializedName("data")
    private PendingLeavesData data;

    public GetPendingLeavesResponse() {
    }

    public PendingLeavesData getData() {
        return data;
    }

    public void setData(PendingLeavesData data) {
        this.data = data;
    }
}
