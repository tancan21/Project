package com.github.tancan21.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String BASE_URL = "https://db.ygoprodeck.com/";
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("application_Onu", Context.MODE_PRIVATE);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        makeApiCall();
    }



       /* List<Yugioh> YugiohList = getDatafromCache();
        if(YugiohList!= null){
            showList(YugiohList);
        }
            else{*/
           // makeApiCall();
        //}
   // }

  /*  private List<Yugioh> getDatafromCache() {

        String jsonYugioh = sharedPreferences.getString("jsonYugiohList", null);

        if(jsonYugioh == null)
        {
            return null;
        } else {
            Type listType = new TypeToken<List<Yugioh>>() {}.getType();
            return gson.fromJson(jsonYugioh, listType);
        }
    }*/

    private void showList(List<Yugioh> yugiohList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // define an adapter
        mAdapter = new ListAdapter(yugiohList);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall(){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        YugiohApi yugiohapi = retrofit.create(YugiohApi.class);

        Call<RestYugiohResponse> call = yugiohapi.getYugiohResponse();
        call.enqueue(new Callback<RestYugiohResponse>() {
            @Override
            public void onResponse(Call<RestYugiohResponse> call, Response<RestYugiohResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Yugioh> yugiohList = response.body().getData();
                   saveList(yugiohList);
                    showList(yugiohList);
                }
                else{
                    showError();
                }
            }


            @Override
            public void onFailure(Call<RestYugiohResponse> call, Throwable t) {
                showError();
            }
        });
    }

  private void saveList(List<Yugioh> yugiohList) {
        String jsonString = gson.toJson(yugiohList);

        sharedPreferences
                .edit()
                .putString("jsonYugiohList", jsonString)
                .apply();

        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();

    }


    private void showError(){
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }
}
