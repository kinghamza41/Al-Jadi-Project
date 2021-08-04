package com.example.aljadiproject.APIIntegration;

import android.util.Log;

import com.example.aljadiproject.SessionManager.UserSession;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.security.AccessController.getContext;

public class Controller {
    String ACCESS_TOKEN;
    private static final String url = "https://dev.aljadiss.com/api/v1/";

    private static Controller clientObj;

    private static Retrofit retrofit;

    Controller() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

    }

    public static synchronized Controller getInstance() {
        if (clientObj == null) {
            clientObj = new Controller();
        }
        return clientObj;
    }

    public apiset getapi() {
        return retrofit.create(apiset.class);
    }

}
