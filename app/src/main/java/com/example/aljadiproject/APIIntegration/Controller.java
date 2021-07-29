package com.example.aljadiproject.APIIntegration;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {

    private static final String url = "http://192.168.10.17/login/";

    private static Controller clientObj;

    private static Retrofit retrofit;

    Controller() {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
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
