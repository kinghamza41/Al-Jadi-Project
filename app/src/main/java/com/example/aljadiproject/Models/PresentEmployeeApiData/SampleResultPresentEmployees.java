package com.example.aljadiproject.Models.PresentEmployeeApiData;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SampleResultPresentEmployees {
    @SerializedName("data")
    ArrayList<PresentEmployeesData> presentEmployeesData;

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


//    public SampleResultPresentEmployees() {
//    }
//
//    public SampleResultPresentEmployees(ArrayList<Datumm> datumList) {
//        this.datumList = datumList;
//    }
//
//    public ArrayList<Datumm> getDatumList() {
//        return datumList;
//    }
//
//    public void setDatumList(ArrayList<Datumm> datumList) {
//        this.datumList = datumList;
//    }
}
