package com.example.aljadiproject.Models;

public class PresentModel {
    String id;
    String name;
    String company;
    String startTime;
    String endTime;

    public PresentModel() {
    }

    public PresentModel(String id, String name, String company, String startTime, String endTime) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
