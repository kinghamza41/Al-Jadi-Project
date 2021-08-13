package com.example.aljadiproject.Models.PendingLeavesApiData;

import com.example.aljadiproject.Models.AbsentEmployeeApiData.AbsentEmployeesData;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PendingLeaves {
    String current_page;
    @SerializedName("data")
    ArrayList<PendingLeavesActualData> pendingLeavesData;

    public PendingLeaves() {
    }

    public String getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(String current_page) {
        this.current_page = current_page;
    }

    public ArrayList<PendingLeavesActualData> getPendingLeavesData() {
        return pendingLeavesData;
    }

    public void setPendingLeavesData(ArrayList<PendingLeavesActualData> pendingLeavesData) {
        this.pendingLeavesData = pendingLeavesData;
    }
}
