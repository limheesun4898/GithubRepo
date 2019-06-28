package com.example.githubrepo.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit retrofit = null;

    public static Retrofit getClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.API_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
