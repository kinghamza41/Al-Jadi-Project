package com.example.aljadiproject.Models.PendingLeavesApiData;

import com.google.gson.annotations.SerializedName;

public class PendingUser {
    Integer employee_id;
    @SerializedName("name")
    String employee_name;

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }
}
