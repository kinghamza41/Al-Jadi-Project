package com.example.aljadiproject.Models;

import com.example.aljadiproject.Models.PresentEmployeeApiData.SampleResultData;
import com.google.gson.annotations.SerializedName;

public class GetPresentEmployeesResponse {
    @SerializedName("data")
    private SampleResultData data;

    public GetPresentEmployeesResponse(SampleResultData data) {
        this.data = data;
    }

    public SampleResultData getData() {
        return data;
    }
}