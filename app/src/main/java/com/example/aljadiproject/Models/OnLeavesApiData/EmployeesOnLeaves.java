package com.example.aljadiproject.Models.OnLeavesApiData;

import com.example.aljadiproject.Models.AbsentEmployeeApiData.AbsentEmployeesData;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EmployeesOnLeaves {
    String current_page;
    @SerializedName("data")
    ArrayList<OnLeavesActualData> onLeavesActualData;

    public String getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(String current_page) {
        this.current_page = current_page;
    }

    public ArrayList<OnLeavesActualData> getOnLeavesActualData() {
        return onLeavesActualData;
    }

    public void setOnLeavesActualData(ArrayList<OnLeavesActualData> onLeavesActualData) {
        this.onLeavesActualData = onLeavesActualData;
    }
}
