package com.example.aljadiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.aljadiproject.APIIntegration.Controller;
import com.example.aljadiproject.Adapter.AbsentAdapter;
import com.example.aljadiproject.Adapter.PendingAdapter;
import com.example.aljadiproject.Models.AbsentEmployeeApiData.AbsentEmployeesData;
import com.example.aljadiproject.Models.GetAbsentEmployeesReponse;
import com.example.aljadiproject.Models.GetPendingLeavesResponse;
import com.example.aljadiproject.Models.PendingLeavesApiData.PendingLeavesActualData;
import com.example.aljadiproject.Models.PresentModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingActivity extends AppCompatActivity {
    RecyclerView presentRecView;
    NestedScrollView nestedScrollView;
    private int pageCount = 1;
    private static int perPage = 80;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending);
        presentRecView = findViewById(R.id.presentrecview);
        progressBar= findViewById(R.id.pbHeaderProgress);
        progressBar.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
//        presentRecView.setHasFixedSize(true);
        presentRecView.setLayoutManager(layoutManager);
        getAbsentEmployees();
    }
    public void getAbsentEmployees() {

        Call<GetPendingLeavesResponse> call = Controller.getInstance().getapi().getPendingLeaves();

        call.enqueue(new Callback<GetPendingLeavesResponse>() {
            @Override
            public void onResponse(Call<GetPendingLeavesResponse> call, Response<GetPendingLeavesResponse> response) {
                ArrayList<PendingLeavesActualData> arrayList = new ArrayList<>();
                arrayList = response.body().getData().getLeaves().getPendingLeavesData();

                PendingAdapter adapter = new PendingAdapter(arrayList, getApplicationContext());
                presentRecView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);


                //    Log.d("RESPONSE_DATA", response.body().getData().getPresent_employees().getPresentEmployeesData().get(0).getCompany_name());
            }

            @Override
            public void onFailure(Call<GetPendingLeavesResponse> call, Throwable t) {
                Log.d("RESPONSE_ERROR", t.getLocalizedMessage());
            }
        });
    }
}