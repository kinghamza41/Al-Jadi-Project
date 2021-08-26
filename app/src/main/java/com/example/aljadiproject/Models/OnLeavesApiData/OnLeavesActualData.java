package com.example.aljadiproject.Models.OnLeavesApiData;

import com.google.gson.annotations.SerializedName;

public class OnLeavesActualData {

    Integer id;
    String leave_type;
    String reason_for_leave;
    String leave_duration_type;
    String start_date;
    @SerializedName("user")
    OnLeavesUser onLeavesUser;

    public OnLeavesUser getOnLeavesUser() {
        return onLeavesUser;
    }

    public void setOnLeavesUser(OnLeavesUser onLeavesUser) {
        this.onLeavesUser = onLeavesUser;
    }

    public Integer getId() {
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
