package com.example.aljadiproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aljadiproject.APIIntegration.Controller;
import com.example.aljadiproject.Models.DashboardApiData.DashboardActualData;
import com.example.aljadiproject.Models.GetDashboardResponse;
import com.example.aljadiproject.Models.LoginApiData.LoginRequest;
import com.example.aljadiproject.SessionManager.UserSession;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends AppCompatActivity {
    private final String sharedprofileName = "haccount";
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    TextView tvPresent, tvAbsent, tvPending, tvTodayLeaves;
    Button btn;
    ProgressBar presentPB, absentPB, pendingPB, onLeavesPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawerlayout);
        setSupportActionBar(toolbar);
        idsInitialization();
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Home:
                        Toast.makeText(Dashboard.this, "Home Panel is open", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.logout:
                        checkUserExistence();
                        break;
                }
                return true;
            }
        });
        getDashboardResponse();
    }

    private void idsInitialization() {
        toolbar = findViewById(R.id.toolbar);
        tvPresent = findViewById(R.id.tvPresent);
        tvAbsent = findViewById(R.id.tvAbsent);
        tvPending = findViewById(R.id.tvPending);
        tvTodayLeaves = findViewById(R.id.tvTodayLeaves);
        presentPB = findViewById(R.id.presentPB);
        presentPB.setVisibility(View.VISIBLE);
        absentPB = findViewById(R.id.absentPB);
        absentPB.setVisibility(View.VISIBLE);
        pendingPB = findViewById(R.id.pendingPB);
        pendingPB.setVisibility(View.VISIBLE);
        onLeavesPB = findViewById(R.id.onLeavesPB);
        onLeavesPB.setVisibility(View.VISIBLE);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer);
    }

    private void getDashboardResponse() {

        Call<GetDashboardResponse> call = Controller.getInstance().getapi().getDashboardData();
        call.enqueue(new Callback<GetDashboardResponse>() {
            @Override
            public void onResponse(Call<GetDashboardResponse> call, Response<GetDashboardResponse> response) {
                if (response.isSuccessful()) {
                    GetDashboardResponse getDashboardResponse = new GetDashboardResponse();
                    getDashboardResponse = response.body();

                    tvPresent.setText(getDashboardResponse.getDashboardActualData().getPresent());
                    presentPB.setVisibility(View.GONE);
                    tvAbsent.setText(getDashboardResponse.getDashboardActualData().getAbsent());
                    absentPB.setVisibility(View.GONE);
                    tvPending.setText(getDashboardResponse.getDashboardActualData().getPending_leaves());
                    pendingPB.setVisibility(View.GONE);
                    tvTodayLeaves.setText(getDashboardResponse.getDashboardActualData().getEmployees_on_Leave());
                    onLeavesPB.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<GetDashboardResponse> call, Throwable t) {
                Toast.makeText(Dashboard.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void checkUserExistence() {
        UserSession userSession = new UserSession(getApplicationContext());
        String ACCESS_TOKEN = userSession.GetKeyVlaue("access_token");

        if (ACCESS_TOKEN != null) {
            LoginRequest loginRequest = new LoginRequest();
            SharedPreferences preferences = getSharedPreferences(sharedprofileName, Context.MODE_PRIVATE);
            preferences.edit().remove("access_token").apply();
            preferences.edit().remove(loginRequest.getEmail()).apply();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    public void presentCardView(View view) {
        CardView cardView = findViewById(R.id.cardPresent);
        Intent intent1 = new Intent(Dashboard.this, PresentActivity.class);
        startActivity(intent1);

    }

    public void absentCardView(View view) {
        Intent intent = new Intent(Dashboard.this, AbsentActivity.class);
        startActivity(intent);

    }

    public void pendingCardView(View view) {
        Intent intent = new Intent(Dashboard.this, PendingActivity.class);
        startActivity(intent);

    }

    public void leavesTodayCardView(View view) {
        Intent intent = new Intent(Dashboard.this, LeavesTodayActivity.class);
        startActivity(intent);

    }
}