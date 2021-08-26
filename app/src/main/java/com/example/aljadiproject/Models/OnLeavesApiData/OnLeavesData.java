package com.example.aljadiproject.Models.OnLeavesApiData;

import com.example.aljadiproject.Models.AbsentEmployeeApiData.AbsentEmployees;

public class OnLeavesData {
    EmployeesOnLeaves leaves;
    int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public OnLeavesData(EmployeesOnLeaves leaves) {
        this.leaves = leaves;
    }

    public EmployeesOnLeaves getLeaves() {
        return leaves;
    }

    public void setLeaves(EmployeesOnLeaves leaves) {
        this.leaves = leaves;
    }
}
