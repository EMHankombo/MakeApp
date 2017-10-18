package com.example.enoch.makeapp.data.network.service;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mainza1992 on 18/10/2017.
 */


public class ConnectionService {



    static Retrofit retrofit;
    static OkHttpClient okHttpClient;

    public static IRequestInterface getConnectionService(){
        // okHttpClient = buildClient();
        retrofit = new Retrofit.Builder()
                .baseUrl(API_CONSTANTS.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                // .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(IRequestInterface.class);
    }
}
