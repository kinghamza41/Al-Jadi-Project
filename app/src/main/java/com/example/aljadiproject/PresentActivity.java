package com.example.aljadiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private int pageCount = 1;
    private static int perPage = 20;
    SampleResultPresentEmployees arrayList;
    String ACCESS_TOKEN;
    TextView test;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present);
        presentRecView = findViewById(R.id.presentrecview);
        progressBar = findViewById(R.id.pbHeaderProgress);
        progressBar.setVisibility(View.VISIBLE);
        // test = findViewById(R.id.test);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        presentRecView.setLayoutManager(layoutManager);
//        PresentAdapter adapter = new PresentAdapter(arrayList, getApplicationContext());
//        presentRecView.setAdapter(adapter);
        getPresentEmployees();


    }

    private void getPresentEmployees() {


        Call<GetPresentEmployeesResponse> call = Controller.getInstance().getapi().getPresentEmployees();

        call.enqueue(new Callback<GetPresentEmployeesResponse>() {
            @Override
            public void onResponse(Call<GetPresentEmployeesResponse> call, Response<GetPresentEmployeesResponse> response) {
                ArrayList<PresentEmployeesData> arrayList = new ArrayList<>();
                arrayList = response.body().getData().getPresent_employees().getPresentEmployeesData();

                PresentAdapter adapter = new PresentAdapter(arrayList, getApplicationContext());
                presentRecView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
                recyclerViewAdapter();

                Log.d("RESPONSE_DATA", response.body().getData().getPresent_employees().getPresentEmployeesData().get(0).getCompany_name());
            }

            @Override
            public void onFailure(Call<GetPresentEmployeesResponse> call, Throwable t) {
                Log.d("RESPONSE_ERROR", t.getLocalizedMessage());
            }
        });
    }

    private void recyclerViewAdapter() {


    }

//& null!=PresentEmployeesModel.Example
}