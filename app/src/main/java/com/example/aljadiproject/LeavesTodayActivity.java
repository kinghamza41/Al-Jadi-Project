package com.example.aljadiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    ArrayList<OnLeavesActualData> arrayList = new ArrayList<>();
    private Integer page = 1;
    private static Integer pageSize = 5;
    private boolean isLastPage = false;
    AppCompatButton prevBtn, nextBtn;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaves_today);
        presentRecView = findViewById(R.id.presentrecview);
        prevBtn = findViewById(R.id.prev_btn);
        nextBtn = findViewById(R.id.next_btn);
        total = findViewById(R.id.total);
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
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    arrayList = response.body().getData().getLeaves().getOnLeavesActualData();
                    String totalRecords = response.body().getData().getLeaves().getTotal().toString();
                    total.setText(totalRecords);
                    LeavesTodayAdapter adapter = new LeavesTodayAdapter(arrayList, getApplicationContext());
                    presentRecView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                }

                if (pageSize >= 1) {
                    isLastPage = arrayList.size() < pageSize;
                } else {
                    isLastPage = true;
                }
                nextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!isLastPage) {
                            page++;
                            getOnLeaves();
                        }
                    }
                });
                prevBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isLastPage) {
                            page--;
                            getOnLeaves();
                        }
                    }
                });

                //    Log.d("RESPONSE_DATA", response.body().getData().getPresent_employees().getPresentEmployeesData().get(0).getCompany_name());
            }

            @Override
            public void onFailure(Call<GetOnLeavesResponse> call, Throwable t) {
                Log.d("RESPONSE_ERROR", t.getLocalizedMessage());
            }
        });
    }
}