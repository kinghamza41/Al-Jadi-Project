package com.example.aljadiproject.Models.AbsentEmployeeApiData;

import com.example.aljadiproject.Models.PresentEmployeeApiData.PresentEmployeesData;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AbsentEmployees {
    String current_page;
    @SerializedName("data")
    ArrayList<AbsentEmployeesData> absentEmployeesData;
    Integer per_page;
    Integer from;
    Integer to;
    Integer total;
    String next_page_url;
    String prev_page_url;

    public Integer getPer_page() {
        return per_page;
    }

    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getNext_page_url() {
        return next_page_url;
    }

    public void setNext_page_url(String next_page_url) {
        this.next_page_url = next_page_url;
    }

    public String getPrev_page_url() {
        return prev_page_url;
    }

    public void setPrev_page_url(String prev_page_url) {
        this.prev_page_url = prev_page_url;
    }

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
