package com.example.aljadiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.NestedScrollView;
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
import com.example.aljadiproject.Adapter.PresentAdapter;
import com.example.aljadiproject.Models.GetPresentEmployeesResponse;
import com.example.aljadiproject.Models.PresentEmployeeApiData.PresentEmployeesData;
import com.example.aljadiproject.Models.PresentEmployeeApiData.SampleResultPresentEmployees;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresentActivity extends AppCompatActivity {
    RecyclerView presentRecView;
    NestedScrollView nestedScrollView;
    ArrayList<PresentEmployeesData> arrayList = new ArrayList<>();
    private Integer page = 1;
    private static final Integer pageSize = 20;
    String ACCESS_TOKEN;
    TextView total;
    ProgressBar progressBar;
    private boolean isLastPage = false;
    AppCompatButton prevBtn, nextBtn;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present);
        presentRecView = findViewById(R.id.presentrecview);
        prevBtn = findViewById(R.id.prev_btn);
        nextBtn = findViewById(R.id.next_btn);
        total = findViewById(R.id.total);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        progressBar = findViewById(R.id.pbHeaderProgress);
        progressBar.setVisibility(View.VISIBLE);
        // test = findViewById(R.id.test);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        presentRecView.setLayoutManager(layoutManager);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresentEmployees();
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        getPresentEmployees();


    }

    private void getPresentEmployees() {


        Call<GetPresentEmployeesResponse> call = Controller.getInstance().getapi().getPresentEmployees();

        call.enqueue(new Callback<GetPresentEmployeesResponse>() {
            @Override
            public void onResponse(Call<GetPresentEmployeesResponse> call, Response<GetPresentEmployeesResponse> response) {
                if (response.isSuccessful()) {
                    ArrayList<PresentEmployeesData> arrayList = new ArrayList<>();
                    arrayList = response.body().getData().getPresent_employees().getPresentEmployeesData();

                    PresentAdapter adapter = new PresentAdapter(arrayList, getApplicationContext());
                    presentRecView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                    recyclerViewAdapter();
                    swipeContainer.setRefreshing(false);
                }

                //  Log.d("RESPONSE_DATA", response.body().getData().getPresent_employees().getPresentEmployeesData().get(0).getCompany_name());

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
                            getPresentEmployees();
                        }
                    }
                });
                prevBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isLastPage) {
                            page--;
                            getPresentEmployees();
                        }
                    }
                });
            }


            @Override
            public void onFailure(Call<GetPresentEmployeesResponse> call, Throwable t) {
                Log.d("RESPONSE_ERROR", t.getLocalizedMessage());
            }
        });
    }

    private void recyclerViewAdapter() {


    }

}