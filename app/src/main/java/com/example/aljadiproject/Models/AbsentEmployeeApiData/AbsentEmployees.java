package com.example.aljadiproject.Models.AbsentEmployeeApiData;

import com.example.aljadiproject.Models.PresentEmployeeApiData.PresentEmployeesData;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AbsentEmployees {
    String current_page;
    @SerializedName("data")
    ArrayList<AbsentEmployeesData> absentEmployeesData;

    public String getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(String current_page) {
        this.current_page = current_page;
    }

    public ArrayList<AbsentEmployeesData> getAbsentEmployeesData() {
        return absentEmployeesData;
    }

    public void setAbsentEmployeesData(ArrayList<AbsentEmployeesData> absentEmployeesData) {
        this.absentEmployeesData = absentEmployeesData;
    }
}
