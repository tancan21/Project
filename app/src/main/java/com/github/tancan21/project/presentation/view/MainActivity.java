package com.github.tancan21.project.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.tancan21.project.Constants;
import com.github.tancan21.project.R;
import com.github.tancan21.project.Singletons;
import com.github.tancan21.project.presentation.controller.MainController;
import com.github.tancan21.project.presentation.model.Pokemon;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = new MainController(this, Singletons.getGson(),Singletons.getSharedPreferences(getApplicationContext()));
        controller.onStart();
    }

    public void showList(List<Pokemon> pokemonList){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mAdapter = new ListAdapter(pokemonList, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Pokemon item) {
                controller.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    public void showError() {
        Toast.makeText(getApplicationContext(), "API ERROR", Toast.LENGTH_SHORT).show();
    }

    public void navigateToDetails(Pokemon pokemon) {
        Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
        myIntent.putExtra(Constants.POKE_KEY, Singletons.getGson().toJson(pokemon));
        MainActivity.this.startActivity(myIntent);
    }
}

