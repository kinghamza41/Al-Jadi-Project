package com.example.aljadiproject.Models.PendingLeavesApiData;

import com.example.aljadiproject.Models.AbsentEmployeeApiData.AbsentEmployeesData;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PendingLeaves {
    Integer current_page;



    Integer per_page;
    Integer from;
    Integer to;
    Integer total;
    String next_page_url;
    String prev_page_url;


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

    @SerializedName("data")
    ArrayList<PendingLeavesActualData> pendingLeavesData;


    public PendingLeaves() {
    }

    public PendingLeaves(Integer current_page, Integer per_page, String next_page_url, String prev_page_url, ArrayList<PendingLeavesActualData> pendingLeavesData) {
        this.current_page = current_page;
        this.per_page = per_page;
        this.next_page_url = next_page_url;
        this.prev_page_url = prev_page_url;
        this.pendingLeavesData = pendingLeavesData;
    }

    public Integer getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(Integer current_page) {
        this.current_page = current_page;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
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

    public ArrayList<PendingLeavesActualData> getPendingLeavesData() {
        return pendingLeavesData;
    }


    public void setPendingLeavesData(ArrayList<PendingLeavesActualData> pendingLeavesData) {
        this.pendingLeavesData = pendingLeavesData;
    }
}
