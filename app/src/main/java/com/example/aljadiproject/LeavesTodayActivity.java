package com.example.aljadiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeavesTodayActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipeContainer;
    RecyclerView presentRecView;
    ProgressBar progressBar;
    ArrayList<OnLeavesActualData> arrayList = new ArrayList<>();
    private Integer page = 1;
    private static final Integer pageSize = 5;
    private boolean isLastPage = false;
    AppCompatButton prevBtn, nextBtn;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaves_today);
        presentRecView = findViewById(R.id.presentrecview);
//        prevBtn = findViewById(R.id.prev_btn);
//        nextBtn = findViewById(R.id.next_btn);
//        total = findViewById(R.id.total);
        progressBar = findViewById(R.id.pbHeaderProgress);
        progressBar.setVisibility(View.VISIBLE);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
//        presentRecView.setHasFixedSize(true);
        presentRecView.setLayoutManager(layoutManager);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getOnLeaves();
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        getOnLeaves();
    }

    public void getOnLeaves() {

        Call<GetOnLeavesResponse> call = Controller.getInstance().getapi().getOnLeaves();

        call.enqueue(new Callback<GetOnLeavesResponse>() {
            @Override
            public void onResponse(@NotNull Call<GetOnLeavesResponse> call, @NotNull Response<GetOnLeavesResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    arrayList = response.body().getData().getLeaves().getOnLeavesActualData();
//                    String totalRecords = response.body().getData().getLeaves().getTotal().toString();
//                    total.setText(totalRecords);
                    LeavesTodayAdapter adapter = new LeavesTodayAdapter(arrayList, getApplicationContext());
                    presentRecView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                    swipeContainer.setRefreshing(false);
                }

                if (pageSize >= 1) {
                    isLastPage = arrayList.size() < pageSize;
                } else {
                    isLastPage = true;
                }
//                nextBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (!isLastPage) {
//                            page++;
//                            getOnLeaves();
//                        }
//                    }
//                });
//                prevBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (isLastPage) {
//                            page--;
//                            getOnLeaves();
//                        }
//                    }
//                });

                //    Log.d("RESPONSE_DATA", response.body().getData().getPresent_employees().getPresentEmployeesData().get(0).getCompany_name());
            }

            @Override
            public void onFailure(Call<GetOnLeavesResponse> call, Throwable t) {
                Log.d("RESPONSE_ERROR", t.getLocalizedMessage());
            }
        });
    }
}