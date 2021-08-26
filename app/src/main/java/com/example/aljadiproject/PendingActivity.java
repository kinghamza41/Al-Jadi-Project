package com.example.aljadiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending);
        presentRecView = findViewById(R.id.presentrecview);
        prevBtn = findViewById(R.id.prev_btn);
        nextBtn = findViewById(R.id.next_btn);
        total = findViewById(R.id.total);
        progressBar = findViewById(R.id.pbHeaderProgress);
        progressBar.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
//        presentRecView.setHasFixedSize(true);
        presentRecView.setLayoutManager(layoutManager);

//        dialog = new ProgressDialog(this);
//        dialog.setMessage("Loading...");
//        dialog.setCancelable(false);
//        dialog.show();
//        dialog.dismiss();

        getPendingLeaves();
        //  sendLeaveID();
//        setUpPagination(true);
//        if (!isLastPage) {
////
//
//            page++;
//            getAbsentEmployees();
//        }
//        presentRecView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                int visibleItem = layoutManager.getChildCount();
//                int totalItem = layoutManager.getItemCount();
//                int firstVisibleItemPos = layoutManager.findFirstVisibleItemPosition();
//
//                if(!isLastPage)
//                {
//                    if((visibleItem + firstVisibleItemPos >= totalItem)
//                        && firstVisibleItemPos>=0 && totalItem>=pageSize) {
////                        page++;
//
//                        getAbsentEmployees();
//                    }
//
//                }
//            }
//        });


    }

    public void sendLeaveID(int leaveId,Context context ) {
        UpdatePendingResponse updatePendingResponse = new UpdatePendingResponse();
        Call<UpdatePendingResponse> call = Controller.getInstance().getapi().sendLeavesId(leaveId, updatePendingResponse);

        call.enqueue(new Callback<UpdatePendingResponse>() {
            @Override
            public void onResponse(@NotNull Call<UpdatePendingResponse> call, @NotNull Response<UpdatePendingResponse> response) {
                //  String msg =  updatePendingResponse.getMessage();
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

//                   if(leaveId>0){
//                       Toast.makeText(PendingActivity.this, "chnges", Toast.LENGTH_SHORT).show();
//                   }
                    Log.d("Success", response.message());

                }
            }

            @Override
            public void onFailure(@NotNull Call<UpdatePendingResponse> call, @NotNull Throwable t) {
                // dialog.dismiss();
                Log.d("RESPONSE_ERROR", t.getLocalizedMessage());
            }
        });
    }

    public void sendLeaveIDForRejection(int leaveId, String leave, Context context) {
        UpdatePendingResponse updatePendingResponse = new UpdatePendingResponse();


        Call<UpdatePendingResponse> call = Controller.getInstance().getapi().sendLeavesIdForRejection(leaveId,  leave);

        call.enqueue(new Callback<UpdatePendingResponse>() {
            @Override
            public void onResponse(@NotNull Call<UpdatePendingResponse> call, @NotNull Response<UpdatePendingResponse> response) {
                //  String msg =  updatePendingResponse.getMessage();
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                   if(leaveId>0){
//                       Toast.makeText(PendingActivity.this, "chnges", Toast.LENGTH_SHORT).show();
//                   }
                    Log.d("Success", response.message());

                }
            }

            @Override
            public void onFailure(@NotNull Call<UpdatePendingResponse> call, @NotNull Throwable t) {
                // dialog.dismiss();
                Log.d("RESPONSE_ERROR", t.getLocalizedMessage());
            }
        });
    }

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
                    String totalRecord = response.body().getData().getLeaves().getTotal().toString();
                    total.setText(totalRecord);
                    //GetPendingLeavesResponse getPendingLeavesResponse = response.body().getData().getLeaves().getCurrent_page();
                    acceptOnClick(arrayList);
                    progressBar.setVisibility(View.GONE);
//                   String total1 =  response.body().getData().getLeaves().getTotal().toString();
//                    total.setText( total1 );
                }   //else if (arrayList.size()<=0){
//                    progressBar.setVisibility(View.INVISIBLE);
//                }

                isLoading = false;

//                dialog.dismiss();
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
                            getPendingLeaves();
                        }
                    }
                });
                prevBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isLastPage) {
                            page--;
                            getPendingLeaves();
                        }
                    }
                });
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
        PendingAdapter adapter = new PendingAdapter(arrayList, getApplicationContext());
        presentRecView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        presentRecView.setHasFixedSize(true);
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