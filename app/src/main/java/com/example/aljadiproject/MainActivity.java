package com.example.aljadiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aljadiproject.APIIntegration.Controller;
import com.example.aljadiproject.Models.LoginApiData.LoginRequest;
import com.example.aljadiproject.Models.LoginResponse;
import com.example.aljadiproject.SessionManager.UserSession;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    //private lateinit var userSession: UserSession
    // private lateinit var apiClient: ApiClient;
    EditText t1, t2;
    AppCompatButton savebtn;
    TextView tv;
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;
    private static final String NOTIFICATION_CHANNEL_ID = "100002";
    UserSession userSession;
    String ACCESS_TOKEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        tv = findViewById(R.id.tv);

        savebtn = findViewById(R.id.savebtn);
        //checkUserExistence();
        savebtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                processLogin();
            }
        });
        PusherOptions options = new PusherOptions();
        options.setCluster("ap2");

        Pusher pusher = new Pusher("2d100a8c74ff5472530d", options);

        pusher.connect(new ConnectionEventListener() {
            @Override
            public void onConnectionStateChange(ConnectionStateChange change) {
                Log.i("Pusher", "State changed from " + change.getPreviousState() +
                        " to " + change.getCurrentState());
            }

            @Override
            public void onError(String message, String code, Exception e) {
                Log.i("Pusher", "There was a problem connecting! " +
                        "\ncode: " + code +
                        "\nmessage: " + message +
                        "\nException: " + e
                );
            }
        }, ConnectionState.ALL);

        Channel channel = pusher.subscribe("my-channel");

        channel.bind("my-event", new SubscriptionEventListener() {
            @Override
            public void onEvent(PusherEvent event) {
                String data = event.getData();
                String nama = "";
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    nama = jsonObject.getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                mBuilder = new NotificationCompat.Builder(MainActivity.this);
                mBuilder.setSmallIcon(R.mipmap.ic_launcher);
                mBuilder.setContentTitle("Al Jadi Notifications Test")
                        .setContentText(nama)
                        .setAutoCancel(false)
                        .setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
                mNotificationManager = (NotificationManager) MainActivity.this
                        .getSystemService(Context.NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel notificationChannel = new NotificationChannel
                            (NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
                    notificationChannel.enableLights(true);
                    notificationChannel.setLightColor(Color.RED);
                    notificationChannel.enableVibration(true);
                    notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

                    assert mNotificationManager != null;
                    mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
                    mNotificationManager.createNotificationChannel(notificationChannel);
                }
                assert mNotificationManager != null;
                mNotificationManager.notify(0, mBuilder.build());
            }
        });
    }


    public void processLogin() {
        String email = t1.getText().toString();
        String password = t2.getText().toString();
        if (email.isEmpty()) {

            t1.setError("Enter is Required");
            t1.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            t1.setError("Enter a valid Email");
            t1.requestFocus();
            return;
        }
        if (password.isEmpty()) {

            t2.setError("Password Required");
            t2.requestFocus();
            return;

        } else {

            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setEmail(t1.getText().toString());
            loginRequest.setPassword(t2.getText().toString());
            Call<LoginResponse> call = Controller.getInstance().getapi().verifyUser(loginRequest);

            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.body().getStatusCode() == 200) {
                        LoginResponse loginResponse = response.body();
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                new UserSession(getApplicationContext()).SaveCredentials(loginResponse.getData().getToken());
                                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                startActivity(intent);

                            }
                        }, 300);
                    } else if (response.body().getStatusCode() != 200) {
                        LoginResponse loginResponse = response.body();
                        Toast.makeText(MainActivity.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override

    protected void onStart() {
        super.onStart();
        UserSession userSession = new UserSession(getApplicationContext());
        ACCESS_TOKEN = userSession.GetKeyVlaue("access_token");
        if (ACCESS_TOKEN != null) {
            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}