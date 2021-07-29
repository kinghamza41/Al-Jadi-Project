package com.example.aljadiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.aljadiproject.Adapter.LeavesTodayAdapter;
import com.example.aljadiproject.Models.PresentModel;

import java.util.ArrayList;

public class LeavesTodayActivity extends AppCompatActivity {
    RecyclerView presentRecView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaves_today);
        presentRecView = findViewById(R.id.presentrecview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        presentRecView.setLayoutManager(layoutManager);
        LeavesTodayAdapter adapter = new LeavesTodayAdapter(dataqueue(), getApplicationContext());
        presentRecView.setAdapter(adapter);
    }
    public ArrayList<PresentModel> dataqueue() {
        ArrayList<PresentModel> holder = new ArrayList<>();

        PresentModel obj1 = new PresentModel();
        obj1.setId("1");
        obj1.setName("Hamza");
        obj1.setCompany("Al Jadi");
        obj1.setStartTime("10:00AM");
        obj1.setEndTime("12:00PM");
        holder.add(obj1);

        PresentModel obj2 = new PresentModel();
        obj2.setId("2");
        obj2.setName("Shahzaib");
        obj2.setCompany("Al Jadi");
        obj2.setStartTime("10:00AM");
        obj2.setEndTime("12:00PM");
        holder.add(obj2);

        PresentModel obj3 = new PresentModel();
        obj3.setId("3");
        obj3.setName("Usama");
        obj3.setCompany("Al Jadi");
        obj3.setStartTime("10:00AM");
        obj3.setEndTime("12:00PM");
        holder.add(obj3);


        PresentModel obj4 = new PresentModel();
        obj4.setId("4");
        obj4.setName("Suleman");
        obj4.setCompany("Al Jadi");
        obj4.setStartTime("11:00AM");
        obj4.setEndTime("12:00PM");
        holder.add(obj4);

        return holder;

    }
}