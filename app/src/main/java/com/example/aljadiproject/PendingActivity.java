package com.example.aljadiproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aljadiproject.APIIntegration.Controller;
import com.example.aljadiproject.Adapter.PendingAdapter;
import com.example.aljadiproject.Models.GetPendingLeavesResponse;
import com.example.aljadiproject.Models.PendingLeavesApiData.PendingLeavesActualData;
import com.example.aljadiproject.Models.PendingLeavesApiData.UpdatePendingResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipeContainer;
    RecyclerView presentRecView;
    NestedScrollView nestedScrollView;
    ArrayList<PendingLeavesActualData> arrayList;
    private Integer page = 1;
    private static final Integer pageSize = 5;
    ProgressBar progressBar;
    private ProgressDialog dialog;
    private boolean isLoading;
    private boolean isLastPage = false;
    AppCompatButton prevBtn, nextBtn;
    TextView total;
    Context context;
    private boolean isScrolling;
    PendingAdapter adapter;
    Handler handler = new Handler();
    int apiDelayed = 5 * 1000; //1 second=1000 milisecond, 5*1000=5seconds
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending);
        presentRecView = findViewById(R.id.presentrecview);
//        prevBtn = findViewById(R.id.prev_btn);
//        nextBtn = findViewById(R.id.next_btn);
//        total = findViewById(R.id.total);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        progressBar = findViewById(R.id.pbHeaderProgress);
        progressBar.setVisibility(View.VISIBLE);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
//        presentRecView.setHasFixedSize(true);
        presentRecView.setLayoutManager(layoutManager);
        presentRecView.setHasFixedSize(true);

//        adapter.notifyDataSetChanged();

//        dialog = new ProgressDialog(this);
//        dialog.setMessage("Loading...");
//        dialog.setCancelable(false);
//        dialog.show();
//        dialog.dismiss();
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPendingLeaves();
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        getPendingLeaves();
        //  sendLeaveID();
//        setUpPagination(true);
//        if (!isLastPage) {
///
//
//            page++;
//            getAbsentEmployees();
//        }
        presentRecView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
//                    isScrolling = true;
//                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItem = layoutManager.getChildCount();
                int totalItems = layoutManager.getItemCount();
                int firstVisibleItemPos = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading && !isLastPage) {
                    if ((visibleItem + firstVisibleItemPos >= totalItems)
                            && firstVisibleItemPos >= 0 && totalItems >= pageSize) {
//                        page++;
////                        getPendingLeaves();
                    }

                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                //do your function;
                getPendingLeaves();
                handler.postDelayed(runnable, apiDelayed);
            }
        }, apiDelayed); // so basically after your getPendingLeaves(), from next time it will be 5 sec repeated
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable); //stop handler when activity not visible
    }

    // Accept Api Call
    public void sendLeaveID(int leaveId, Context context) {
//        UpdatePendingResponse updatePendingResponse = new UpdatePendingResponse();
//        ArrayList<PendingLeavesActualData> arrayList= new ArrayList<>();
        Call<GetPendingLeavesResponse> call = Controller.getInstance().getapi().sendLeavesId(leaveId);

        call.enqueue(new Callback<GetPendingLeavesResponse>() {
            @Override
            public void onResponse(@NotNull Call<GetPendingLeavesResponse> call, @NotNull Response<GetPendingLeavesResponse> response) {
                //  String msg =  updatePendingResponse.getMessage();
                assert response.body() != null;
                if (response.isSuccessful()) {
                    //  arrayList = response.body().getData().getLeaves().getPendingLeavesData();
                    Toast.makeText(context, "Leave Approved Successfully", Toast.LENGTH_SHORT).show();
                   //  adapter.notifyDataSetChanged();
                    getPendingLeaves();
//                    PendingAdapter adapter = new PendingAdapter(arrayList, getApplicationContext());
//                    presentRecView.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
//                   if(leaveId>0){
//                       Toast.makeText(PendingActivity.this, "chnges", Toast.LENGTH_SHORT).show();
//                   }
                    Log.d("Success", response.message());
                }
            }

            @Override
            public void onFailure(@NotNull Call<GetPendingLeavesResponse> call, @NotNull Throwable t) {
                // dialog.dismiss();
                Log.d("RESPONSE_ERROR", t.getLocalizedMessage());
            }
        });
    }

    // Reject Api Call
    public void sendLeaveIDForRejection(int leaveId, String leave, Context context) {
        UpdatePendingResponse updatePendingResponse = new UpdatePendingResponse();
        Call<UpdatePendingResponse> call = Controller.getInstance().getapi().sendLeavesIdForRejection(leaveId, leave);

        call.enqueue(new Callback<UpdatePendingResponse>() {
            @Override
            public void onResponse(@NotNull Call<UpdatePendingResponse> call, @NotNull Response<UpdatePendingResponse> response) {
                //  String msg =  updatePendingResponse.getMessage();
                if (response.isSuccessful()) {
                    assert response.body() != null;

                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                //    adapter.notifyDataSetChanged();
//                   if(leaveId>0){
//                       Toast.makeText(PendingActivity.this, "chnges", Toast.LENGTH_SHORT).show();
//                   }
                    Log.d("Success", response.message());

                }
            }

            @Override
            public void onFailure(@NotNull Call<UpdatePendingResponse> call, @NotNull Throwable t) {
                // dialog.dismiss();
                if (t != null) {
                    Log.d("RESPONSE_ERROR", t.getLocalizedMessage());
                }
            }
        });
    }

    //Get Pending Leaves Api Response
    public void getPendingLeaves() {
        isLoading = true;
        Call<GetPendingLeavesResponse> call = Controller.getInstance().getapi().getPendingLeaves(page);

        call.enqueue(new Callback<GetPendingLeavesResponse>() {
            @Override
            public void onResponse(@NotNull Call<GetPendingLeavesResponse> call, @NotNull Response<GetPendingLeavesResponse> response) {
//                String nextPageUrl = response.body().getData().getLeaves().getNext_page_url();
//                String prevPageUrl = response.body().getData().getLeaves().getPrev_page_url();

                if (response.isSuccessful()) {

                    assert response.body() != null;
                    arrayList = response.body().getData().getLeaves().getPendingLeavesData();
                    //String totalRecord = response.body().getData().getLeaves().getTotal().toString();
                    //  total.setText(totalRecord);
                    adapter = new PendingAdapter(arrayList, getApplicationContext());
                    presentRecView.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();
                    //GetPendingLeavesResponse getPendingLeavesResponse = response.body().getData().getLeaves().getCurrent_page();
                    //acceptOnClick(arrayList);
                    progressBar.setVisibility(View.GONE);
                    swipeContainer.setRefreshing(false);
//                   String total1 =  response.body().getData().getLeaves().getTotal().toString();
//                    total.setText( total1 );
                }   //else if (arrayList.size()<=0){
//                    progressBar.setVisibility(View.INVISIBLE);
//                }

                isLoading = false;

                //dialog.dismiss();
//                arrayList.size();
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
//                            getPendingLeaves();
//                        }
//                    }
//                });
//                prevBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (isLastPage) {
//                            page--;
//                            getPendingLeaves();
//                        }
//                    }
//                });
                //    Log.d("RESPONSE_DATA", response.body().getData().getPresent_employees().getPresentEmployeesData().get(0).getCompany_name());
            }

            @Override
            public void onFailure(@NotNull Call<GetPendingLeavesResponse> call, Throwable t) {
                // dialog.dismiss();
                Log.d("RESPONSE_ERROR", t.getLocalizedMessage());
            }
        });
    }

    public void acceptOnClick(ArrayList<PendingLeavesActualData> arrayList) {

//        new AlertDialog.Builder(null)
//                .setTitle("Delete entry")
//                .setMessage("Are you sure you want to delete this entry?")
//
//                // Specifying a listener allows you to take an action before dismissing the dialog.
//                // The dialog is automatically dismissed when a dialog button is clicked.
//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        // Continue with delete operation
//                    }
//                })
//
//                // A null listener allows the button to dismiss the dialog and take no further action.
//                .setNegativeButton(android.R.string.no, null)
//                .setIcon(android.R.drawable.ic_dialog_alert)
//                .show();
    }


//    private void setUpPagination(boolean isPaginationAllowed) {
//        if(isPaginationAllowed){
//            nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
//                if(scrollY==v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()){
//                     //getAbsentEmployees(++pageCount);
//                    Toast.makeText(this, "" + pageCount, Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//        else{
//            nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
//            });
//        }
//    }
}