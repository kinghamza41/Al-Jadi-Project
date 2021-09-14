package com.example.aljadiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aljadiproject.APIIntegration.Controller;
import com.example.aljadiproject.Adapter.AbsentAdapter;
import com.example.aljadiproject.Models.AbsentEmployeeApiData.AbsentEmployeesData;
import com.example.aljadiproject.Models.GetAbsentEmployeesResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbsentActivity extends AppCompatActivity {
    RecyclerView presentRecView;
    NestedScrollView nestedScrollView;
    private int page = 1;
    private  int pageSize = 20;
    private boolean isLastPage;
    ProgressBar progressBar;
    AppCompatButton prevBtn, nextBtn;
    TextView total;
    private boolean isScrolling;
    public int getTotalPageCount;
    public boolean isLoading;
    private SwipeRefreshLayout swipeContainer;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absent);
        presentRecView = findViewById(R.id.presentrecview);
//        prevBtn = findViewById(R.id.prev_btn);
//        nextBtn = findViewById(R.id.next_btn);
//        total = findViewById(R.id.total);
        nestedScrollView = findViewById(R.id.nest);
        progressBar = findViewById(R.id.pbHeaderProgress);
        progressBar.setVisibility(View.VISIBLE);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
//        presentRecView.setHasFixedSize(true);
        presentRecView.setLayoutManager(layoutManager);


        setUpPagination();
//        dialog = new ProgressDialog(this);
//        dialog.setMessage("Loading...");
//        dialog.setCancelable(false);
//        dialog.show();
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAbsentEmployees();
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        getAbsentEmployees();
//        presentRecView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
////                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
////                    isScrolling = true;
////                }
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                int visibleItem = layoutManager.getChildCount();
//                int totalItems = layoutManager.getItemCount();
//                int firstVisibleItemPos = layoutManager.findFirstVisibleItemPosition();
//
//                if (!isLoading && !isLastPage) {
//                    if ((visibleItem + firstVisibleItemPos >= totalItems)
//                            && firstVisibleItemPos >= 0 && totalItems >= pageSize) {
//                        page++;
//                        getAbsentEmployees();
//                    }
//
//                }
//            }
//        });
    }


    private void setUpPagination() {
        nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if(scrollY==v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()){


                page++;
                Toast.makeText(this, " " +page , Toast.LENGTH_SHORT).show();
                getAbsentEmployees();
            }
        });
    }

    public void getAbsentEmployees() {
     //   isLoading = true;
        Call<GetAbsentEmployeesResponse> call = Controller.getInstance().getapi().getAbsentEmployees(page);

        call.enqueue(new Callback<GetAbsentEmployeesResponse>() {
            @Override
            public void onResponse(@NotNull Call<GetAbsentEmployeesResponse> call, @NotNull Response<GetAbsentEmployeesResponse> response) {
               ArrayList<AbsentEmployeesData> arrayList = new ArrayList<>();
                if (response.isSuccessful()) {
                    assert response.body() != null;
                   arrayList = response.body().getData().getAbsent_employees().getAbsentEmployeesData();
                    AbsentAdapter adapter = new AbsentAdapter( arrayList, getApplicationContext());
                    presentRecView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    swipeContainer.setRefreshing(false);
//                    String total1 = response.body().getData().getAbsent_employees().getTotal().toString();
//                    total.setText(total1);
                }
                isLoading = false;

              //  dialog.dismiss();
                if (arrayList.size() > 0) {
                    isLastPage = arrayList.size() < pageSize;
                } else {
                    isLastPage = true;
                }
//                nextBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (!isLastPage) {
//                            page++;
//                            getAbsentEmployees();
//                        }
//                    }
//                });
//                prevBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (isLastPage) {
//                            page--;
//                            getAbsentEmployees();
//                        }
//                    }
//                });
            }
            //    Log.d("RESPONSE_DATA", response.body().getData().getPresent_employees().getPresentEmployeesData().get(0).getCompany_name());


            @Override
            public void onFailure(@NotNull Call<GetAbsentEmployeesResponse> call, @NotNull Throwable t) {
                Log.d("RESPONSE_ERROR", t.getLocalizedMessage());
            }
        });
    }
}