package com.example.githubrepo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.githubrepo.adapter.RepoAdapter;
import com.example.githubrepo.model.UserInfo;
import com.example.githubrepo.model.UserRepo;
import com.example.githubrepo.network.ApiClient;
import com.example.githubrepo.network.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.nio.file.DirectoryIteratorException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.search_id)
    EditText search_id;
    @BindView(R.id.tv_null)
    TextView tv_null;
    @BindView(R.id.layout_userinfo)
    View layout_userinfo;

    ImageView iv_photo;
    TextView tv_name;

    Context context = this;

    ArrayList<UserRepo> arrayList = new ArrayList<>();
    RepoAdapter adatper;
    RecyclerView recyclerView_repo;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        iv_photo = layout_userinfo.findViewById(R.id.iv_photo);
        tv_name = layout_userinfo.findViewById(R.id.tv_name);


    }

    @OnClick(R.id.btn_id)
    void Clicked() {

         id = search_id.getText().toString();
         arrayList.clear();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<JsonObject> userInfo = apiInterface.userInfo(id);
        userInfo.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                tv_null.setVisibility(View.VISIBLE);
                layout_userinfo.setVisibility(View.INVISIBLE);

                if (response.isSuccessful()) {

                    tv_null.setVisibility(View.INVISIBLE);
                    layout_userinfo.setVisibility(View.VISIBLE);

                    JsonElement element = new JsonParser().parse(response.body().toString())
                            .getAsJsonObject();

                    UserInfo info = new Gson().fromJson(element, UserInfo.class);

                    String nickname = info.getName();
                    tv_name.setText(nickname);

                    String imageUrl = info.getAvatar_url();
                    Glide.with(context).load(imageUrl).into(iv_photo);

                    Repo();

                    recyclerView_repo = findViewById(R.id.recyclerview_repo);
                    recyclerView_repo.setHasFixedSize(true);
                    adatper = new RepoAdapter(arrayList, context);
                    recyclerView_repo.setLayoutManager(new LinearLayoutManager(context));
                    recyclerView_repo.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL)); // recyclerview 구분선 지정
                    recyclerView_repo.setAdapter(adatper);

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

    }

    public void Repo(){
        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<List<UserRepo>> userRepo = api.userRepo(id);
        userRepo.enqueue(new Callback<List<UserRepo>>() {
            @Override
            public void onResponse(Call<List<UserRepo>> call, Response<List<UserRepo>> response) {

                if (response.isSuccessful()) {

                    List<UserRepo> userRepoList = response.body();

                    for (UserRepo userRepo1 : userRepoList) {
                        arrayList.add(new UserRepo(userRepo1.name, userRepo1.description, userRepo1.language, userRepo1.updated_at));
                    }
                    adatper.notifyDataSetChanged();

                }


            }

            @Override
            public void onFailure(Call<List<UserRepo>> call, Throwable t) {

            }
        });

    }


}
