package com.example.aljadiproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PresentEmployeesModel {


    public class Data {

        @SerializedName("present_employees")
        @Expose
        private PresentEmployees presentEmployees;

        public PresentEmployees getPresentEmployees() {
            return presentEmployees;
        }

        public void setPresentEmployees(PresentEmployees presentEmployees) {
            this.presentEmployees = presentEmployees;
        }

    }

    public class Datum {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("company_id")
        @Expose
        private Integer companyId;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("company_name")
        @Expose
        private String companyName;
        @SerializedName("employee_name")
        @Expose
        private String employeeName;
        @SerializedName("start_time")
        @Expose
        private String startTime;
        @SerializedName("end_time")
        @Expose
        private String endTime;
        @SerializedName("working_hours")
        @Expose
        private String workingHours;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("user")
        @Expose
        private User user;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getCompanyId() {
            return companyId;
        }

        public void setCompanyId(Integer companyId) {
            this.companyId = companyId;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
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

        public String getWorkingHours() {
            return workingHours;
        }

        public void setWorkingHours(String workingHours) {
            this.workingHours = workingHours;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

    }

    public class Example {

        @SerializedName("status_code")
        @Expose
        private Integer statusCode;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("data")
        @Expose
        private Data data;

        public Integer getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(Integer statusCode) {
            this.statusCode = statusCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

    }

    public class Link {

        @SerializedName("url")
        @Expose
        private Object url;
        @SerializedName("label")
        @Expose
        private String label;
        @SerializedName("active")
        @Expose
        private Boolean active;

        public Object getUrl() {
            return url;
        }

        public void setUrl(Object url) {
            this.url = url;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Boolean getActive() {
            return active;
        }

        public void setActive(Boolean active) {
            this.active = active;
        }

    }


    public class PresentEmployees {

        @SerializedName("current_page")
        @Expose
        private Integer currentPage;
        @SerializedName("data")
        @Expose
        private ArrayList<Datum> data = null;
        @SerializedName("first_page_url")
        @Expose
        private String firstPageUrl;
        @SerializedName("from")
        @Expose
        private Integer from;
        @SerializedName("last_page")
        @Expose
        private Integer lastPage;
        @SerializedName("last_page_url")
        @Expose
        private String lastPageUrl;
        @SerializedName("links")
        @Expose
        private List<Link> links = null;
        @SerializedName("next_page_url")
        @Expose
        private Object nextPageUrl;
        @SerializedName("path")
        @Expose
        private String path;
        @SerializedName("per_page")
        @Expose
        private Integer perPage;
        @SerializedName("prev_page_url")
        @Expose
        private Object prevPageUrl;
        @SerializedName("to")
        @Expose
        private Integer to;
        @SerializedName("total")
        @Expose
        private Integer total;

        public Integer getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(Integer currentPage) {
            this.currentPage = currentPage;
        }

        public ArrayList<Datum> getData() {
            return data;
        }

        public void setData(ArrayList<Datum> data) {
            this.data = data;
        }

        public String getFirstPageUrl() {
            return firstPageUrl;
        }

        public void setFirstPageUrl(String firstPageUrl) {
            this.firstPageUrl = firstPageUrl;
        }

        public Integer getFrom() {
            return from;
        }

        public void setFrom(Integer from) {
            this.from = from;
        }

        public Integer getLastPage() {
            return lastPage;
        }

        public void setLastPage(Integer lastPage) {
            this.lastPage = lastPage;
        }

        public String getLastPageUrl() {
            return lastPageUrl;
        }

        public void setLastPageUrl(String lastPageUrl) {
            this.lastPageUrl = lastPageUrl;
        }

        public List<Link> getLinks() {
            return links;
        }

        public void setLinks(List<Link> links) {
            this.links = links;
        }

        public Object getNextPageUrl() {
            return nextPageUrl;
        }

        public void setNextPageUrl(Object nextPageUrl) {
            this.nextPageUrl = nextPageUrl;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public Integer getPerPage() {
            return perPage;
        }

        public void setPerPage(Integer perPage) {
            this.perPage = perPage;
        }

        public Object getPrevPageUrl() {
            return prevPageUrl;
        }

        public void setPrevPageUrl(Object prevPageUrl) {
            this.prevPageUrl = prevPageUrl;
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

    }


    public class User {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("role_id")
        @Expose
        private Integer roleId;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("employee_id")
        @Expose
        private String employeeId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("email_verified_at")
        @Expose
        private Object emailVerifiedAt;
        @SerializedName("designation")
        @Expose
        private String designation;
        @SerializedName("general_salary")
        @Expose
        private Integer generalSalary;
        @SerializedName("general_shift_start_time")
        @Expose
        private String generalShiftStartTime;
        @SerializedName("general_shift_end_time")
        @Expose
        private String generalShiftEndTime;
        @SerializedName("allowed_overtime")
        @Expose
        private String allowedOvertime;
        @SerializedName("sick_leaves")
        @Expose
        private Integer sickLeaves;
        @SerializedName("compensatory_leaves")
        @Expose
        private Integer compensatoryLeaves;
        @SerializedName("annual_leaves")
        @Expose
        private Integer annualLeaves;
        @SerializedName("casual_leaves")
        @Expose
        private Integer casualLeaves;
        @SerializedName("total_leaves")
        @Expose
        private Integer totalLeaves;
        @SerializedName("joining_date")
        @Expose
        private String joiningDate;
        @SerializedName("profile_picture")
        @Expose
        private String profilePicture;
        @SerializedName("deleted_at")
        @Expose
        private Object deletedAt;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getRoleId() {
            return roleId;
        }

        public void setRoleId(Integer roleId) {
            this.roleId = roleId;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(String employeeId) {
            this.employeeId = employeeId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Object getEmailVerifiedAt() {
            return emailVerifiedAt;
        }

        public void setEmailVerifiedAt(Object emailVerifiedAt) {
            this.emailVerifiedAt = emailVerifiedAt;
        }

        public String getDesignation() {
            return designation;
        }

        public void setDesignation(String designation) {
            this.designation = designation;
        }

        public Integer getGeneralSalary() {
            return generalSalary;
        }

        public void setGeneralSalary(Integer generalSalary) {
            this.generalSalary = generalSalary;
        }

        public String getGeneralShiftStartTime() {
            return generalShiftStartTime;
        }

        public void setGeneralShiftStartTime(String generalShiftStartTime) {
            this.generalShiftStartTime = generalShiftStartTime;
        }

        public String getGeneralShiftEndTime() {
            return generalShiftEndTime;
        }

        public void setGeneralShiftEndTime(String generalShiftEndTime) {
            this.generalShiftEndTime = generalShiftEndTime;
        }

        public String getAllowedOvertime() {
            return allowedOvertime;
        }

        public void setAllowedOvertime(String allowedOvertime) {
            this.allowedOvertime = allowedOvertime;
        }

        public Integer getSickLeaves() {
            return sickLeaves;
        }

        public void setSickLeaves(Integer sickLeaves) {
            this.sickLeaves = sickLeaves;
        }

        public Integer getCompensatoryLeaves() {
            return compensatoryLeaves;
        }

        public void setCompensatoryLeaves(Integer compensatoryLeaves) {
            this.compensatoryLeaves = compensatoryLeaves;
        }

        public Integer getAnnualLeaves() {
            return annualLeaves;
        }

        public void setAnnualLeaves(Integer annualLeaves) {
            this.annualLeaves = annualLeaves;
        }

        public Integer getCasualLeaves() {
            return casualLeaves;
        }

        public void setCasualLeaves(Integer casualLeaves) {
            this.casualLeaves = casualLeaves;
        }

        public Integer getTotalLeaves() {
            return totalLeaves;
        }

        public void setTotalLeaves(Integer totalLeaves) {
            this.totalLeaves = totalLeaves;
        }

        public String getJoiningDate() {
            return joiningDate;
        }

        public void setJoiningDate(String joiningDate) {
            this.joiningDate = joiningDate;
        }

        public String getProfilePicture() {
            return profilePicture;
        }

        public void setProfilePicture(String profilePicture) {
            this.profilePicture = profilePicture;
        }

        public Object getDeletedAt() {
            return deletedAt;
        }

        public void setDeletedAt(Object deletedAt) {
            this.deletedAt = deletedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

    }
}
