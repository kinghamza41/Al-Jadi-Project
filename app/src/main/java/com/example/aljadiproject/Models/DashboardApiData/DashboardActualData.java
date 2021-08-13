package com.example.aljadiproject.Models.DashboardApiData;

public class DashboardActualData {

    String present;
    String absent;
    String employees_on_Leave;
    String pending_leaves;

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }

    public String getAbsent() {
        return absent;
    }

    public void setAbsent(String absent) {
        this.absent = absent;
    }

    public String getEmployees_on_Leave() {
        return employees_on_Leave;
    }

    public void setEmployees_on_Leave(String employees_on_Leave) {
        this.employees_on_Leave = employees_on_Leave;
    }

    public String getPending_leaves() {
        return pending_leaves;
    }

    public void setPending_leaves(String pending_leaves) {
        this.pending_leaves = pending_leaves;
    }
}
