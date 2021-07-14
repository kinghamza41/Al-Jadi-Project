package com.example.aljadiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText t1, t2;
    AppCompatButton savebtn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        tv = findViewById(R.id.tv);
        savebtn = findViewById(R.id.savebtn);
        checkUserExistence();
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processLogin();
            }
        });

    }

    public void processLogin() {
        String email = t1.getText().toString();
        String password = t2.getText().toString();
        Call<ResponseModel> call = Controller.getInstance()
                .getapi()
                .verifyuser(email, password);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel obj = response.body();
                String output = obj.getMessage();
                if (output.equals("failed")) {
                    t1.setText("");
                    t2.setText("");
                    tv.setTextColor(Color.RED);
                    tv.setText("Invalid username or password");
                }
                if (output.equals("exist")) {
                    SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("username", t1.getText().toString());
                    editor.putString("password", t2.getText().toString());
                    editor.commit();
                    editor.apply();
                    startActivity(new Intent(getApplicationContext(), Dashboard.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                tv.setText("Something went wrong");
                tv.setTextColor(Color.RED);
            }
        });
    }

    public void checkUserExistence() {
        SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
        if (sp.contains("username")) {
            startActivity(new Intent(getApplicationContext(), Dashboard.class));
        } else {
            tv.setText("Please Login");
            tv.setTextColor(Color.RED);
        }
    }
}