package com.example.aljadiproject.Models.AbsentEmployeeApiData;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

public class AbsentEmployeesData {
    Context context;
    Integer id;
    String name;
    String designation;
    @SerializedName("general_shift_start_time")
    String start_time;
    @SerializedName("general_shift_end_time")
    String end_time;

//    public Context getContext() {
//        return context;
//    }

//    public void setContext(Context context) {
//        this.context = context;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
