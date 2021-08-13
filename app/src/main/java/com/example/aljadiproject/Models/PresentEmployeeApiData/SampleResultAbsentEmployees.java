package com.example.aljadiproject.Models.PresentEmployeeApiData;

import com.example.aljadiproject.Models.AbsentEmployeeApiData.AbsentEmployeesData;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SampleResultAbsentEmployees {
    @SerializedName("data")
    ArrayList<PresentEmployeesData> presentEmployeesData;

    public ArrayList<com.example.aljadiproject.Models.AbsentEmployeeApiData.AbsentEmployeesData> getAbsentEmployeesData() {
        return AbsentEmployeesData;
    }

    public void setAbsentEmployeesData(ArrayList<com.example.aljadiproject.Models.AbsentEmployeeApiData.AbsentEmployeesData> absentEmployeesData) {
        AbsentEmployeesData = absentEmployeesData;
    }

    @SerializedName("data")
    ArrayList<AbsentEmployeesData> AbsentEmployeesData;

    String current_page;

    public ArrayList<PresentEmployeesData> getPresentEmployeesData() {
        return presentEmployeesData;
    }

    public void setPresentEmployeesData(ArrayList<PresentEmployeesData> presentEmployeesData) {
        this.presentEmployeesData = presentEmployeesData;
    }

//   @SerializedName("data")
//    ArrayList<Datumm> datumList;

    public void setCurrent_page(String current_page) {
        this.current_page = current_page;
    }

    public String getCurrent_page() {
        return current_page;
    }
}
