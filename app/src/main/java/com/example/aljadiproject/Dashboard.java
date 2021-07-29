package com.example.aljadiproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity {
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    TextView tv;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer);
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
                    case R.id.aboutus:
                        Toast.makeText(Dashboard.this, "Setting Panel is open", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.call:
                        Toast.makeText(Dashboard.this, "Call Panel is open", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.logout:
                        SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
                        sp.edit().remove("username").commit();
                        sp.edit().remove("password").commit();
                        sp.edit().apply();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                        break;
                }
                return true;
            }
        });

        // tv = findViewById(R.id.uemail);
        // btn = findViewById(R.id.btnlogout);
        checkUserExistence();
    }

    public void checkUserExistence() {
//        SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
//        if (sp.contains("username")) {
//            tv.setText(sp.getString("username", ""));
//        } else {
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
//        setContentView(R.layout.activity_dashboard);
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