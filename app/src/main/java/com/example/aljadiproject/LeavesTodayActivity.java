package com.example.aljadiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.aljadiproject.APIIntegration.Controller;
import com.example.aljadiproject.Adapter.LeavesTodayAdapter;
import com.example.aljadiproject.Adapter.PendingAdapter;
import com.example.aljadiproject.Models.GetOnLeavesResponse;
import com.example.aljadiproject.Models.GetPendingLeavesResponse;
import com.example.aljadiproject.Models.OnLeavesApiData.OnLeavesActualData;
import com.example.aljadiproject.Models.PendingLeavesApiData.PendingLeavesActualData;
import com.example.aljadiproject.Models.PresentModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeavesTodayActivity extends AppCompatActivity {
    RecyclerView presentRecView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaves_today);
        presentRecView = findViewById(R.id.presentrecview);
        progressBar = findViewById(R.id.pbHeaderProgress);
        progressBar.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
//        presentRecView.setHasFixedSize(true);
        presentRecView.setLayoutManager(layoutManager);
        getOnLeaves();
    }
    public void getOnLeaves() {

        Call<GetOnLeavesResponse> call = Controller.getInstance().getapi().getOnLeaves();

        call.enqueue(new Callback<GetOnLeavesResponse>() {
            @Override
            public void onResponse(Call<GetOnLeavesResponse> call, Response<GetOnLeavesResponse> response) {
                ArrayList<OnLeavesActualData> arrayList = new ArrayList<>();
                arrayList = response.body().getData().getLeaves().getOnLeavesActualData();

                LeavesTodayAdapter adapter = new LeavesTodayAdapter(arrayList, getApplicationContext());
                presentRecView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);


                //    Log.d("RESPONSE_DATA", response.body().getData().getPresent_employees().getPresentEmployeesData().get(0).getCompany_name());
            }

            @Override
            public void onFailure(Call<GetOnLeavesResponse> call, Throwable t) {
                Log.d("RESPONSE_ERROR", t.getLocalizedMessage());
            }
        });
    }
}