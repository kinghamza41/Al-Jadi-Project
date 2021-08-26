package com.example.aljadiproject.Models.PendingLeavesApiData;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PendingLeavesActualData {

    Context context;
    int id;
    Integer user_id;
    String leave_type;
    String reason_for_leave;
    String leave_duration_type;
    String start_date;
    String reason_for_rejection;

    public String getReason_for_rejection() {
        return reason_for_rejection;
    }

    public void setReason_for_rejection(String reason_for_rejection) {
        this.reason_for_rejection = reason_for_rejection;
    }

    @SerializedName("user")
    PendingUser pendingUser;

    public PendingUser getPendingUser() {
        return pendingUser;
    }

    public void setPendingUser(PendingUser pendingUser) {
        this.pendingUser = pendingUser;
    }

//    public Context getContext() {
//        return context;
//    }
//
//    public void setContext(Context context) {
//        this.context = context;
//    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
//    public Context getContext() {
//        return context;
//    }
//
//    public void setContext(Context context) {
//        this.context = context;
//    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLeave_type() {
        return leave_type;
    }

    public void setLeave_type(String leave_type) {
        this.leave_type = leave_type;
    }

    public String getReason_for_leave() {
        return reason_for_leave;
    }

    public void setReason_for_leave(String reason_for_leave) {
        this.reason_for_leave = reason_for_leave;
    }

    public String getLeave_duration_type() {
        return leave_duration_type;
    }

    public void setLeave_duration_type(String leave_duration_type) {
        this.leave_duration_type = leave_duration_type;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }
}
