package com.example.aljadiproject.Models.PresentEmployeeApiData;

import android.content.Context;

public class SampleResultData {
    Context context;

    SampleResultPresentEmployees present_employees;

    public void setPresentEmployees(SampleResultPresentEmployees present_employees) {
        this.present_employees = present_employees;
    }

    public SampleResultPresentEmployees getPresent_employees() {
        return present_employees;
    }
}
