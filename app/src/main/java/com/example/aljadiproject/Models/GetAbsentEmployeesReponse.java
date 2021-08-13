package com.example.aljadiproject.Models;

import com.example.aljadiproject.Models.AbsentEmployeeApiData.AbsentData;
import com.example.aljadiproject.Models.PresentEmployeeApiData.SampleResultData;
import com.google.gson.annotations.SerializedName;

public class GetAbsentEmployeesReponse {

    @SerializedName("data")
    private AbsentData data;

    public GetAbsentEmployeesReponse() {
    }

    public AbsentData getData() {
        return data;
    }

    public void setData(AbsentData data) {
        this.data = data;
    }
}
