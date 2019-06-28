package com.example.githubrepo.network;

import com.example.githubrepo.model.UserRepo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    String API_URI = "https://api.github.com/users/";

    @GET("{username}")
    Call<JsonObject> userInfo (@Path("username") String username);


    @GET("{username}/repos")
    Call<List<UserRepo>> userRepo (@Path("username") String username);

}
