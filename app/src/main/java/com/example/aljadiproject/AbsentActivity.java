package com.example.aljadiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aljadiproject.APIIntegration.Controller;
import com.example.aljadiproject.APIIntegration.apiset;
import com.example.aljadiproject.Adapter.AbsentAdapter;
import com.example.aljadiproject.Adapter.PresentAdapter;
import com.example.aljadiproject.Models.AbsentEmployeeApiData.AbsentEmployeesData;
import com.example.aljadiproject.Models.GetAbsentEmployeesReponse;
import com.example.aljadiproject.Models.GetPresentEmployeesResponse;
import com.example.aljadiproject.Models.PresentEmployeeApiData.PresentEmployeesData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AbsentActivity extends AppCompatActivity {
    RecyclerView presentRecView;
    NestedScrollView nestedScrollView;
    private int pageCount = 1;
    private static int perPage = 80;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absent);
        presentRecView = findViewById(R.id.presentrecview);
        nestedScrollView = findViewById(R.id.nestedAbsentSV);
        progressBar = findViewById(R.id.pbHeaderProgress);
        progressBar.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
//        presentRecView.setHasFixedSize(true);
        presentRecView.setLayoutManager(layoutManager);



     //   setUpPagination(true);
        getAbsentEmployees();
    }

//    private void setUpPagination(boolean isPaginationAllowed) {
//        if(isPaginationAllowed){
//            nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
//                if(scrollY==v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()){
//
//                    Toast.makeText(this, "" + pageCount, Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//        else{
//            nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
//            });
//        }
//    }

    public void getAbsentEmployees() {

        Call<GetAbsentEmployeesReponse> call = Controller.getInstance().getapi().getAbsentEmployees();

        call.enqueue(new Callback<GetAbsentEmployeesReponse>() {
            @Override
            public void onResponse(Call<GetAbsentEmployeesReponse> call, Response<GetAbsentEmployeesReponse> response) {
                ArrayList<AbsentEmployeesData> arrayList = new ArrayList<>();
                arrayList = response.body().getData().getAbsent_employees().getAbsentEmployeesData();

                AbsentAdapter adapter = new AbsentAdapter(arrayList, getApplicationContext());
                presentRecView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);


            //    Log.d("RESPONSE_DATA", response.body().getData().getPresent_employees().getPresentEmployeesData().get(0).getCompany_name());
            }

            @Override
            public void onFailure(Call<GetAbsentEmployeesReponse> call, Throwable t) {
                Log.d("RESPONSE_ERROR", t.getLocalizedMessage());
            }
        });
    }
}