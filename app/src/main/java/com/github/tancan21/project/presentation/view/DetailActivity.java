package com.github.tancan21.project.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


import com.github.tancan21.project.Constants;
import com.github.tancan21.project.R;
import com.github.tancan21.project.Singletons;
import com.github.tancan21.project.presentation.model.Pokemon;

public class DetailActivity extends AppCompatActivity {

    private TextView txtDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        txtDetail = findViewById(R.id.detail_txt);
        Intent intent = getIntent();
        String pokemonJson = intent.getStringExtra(Constants.POKE_KEY);
        Pokemon pokemon = Singletons.getGson().fromJson(pokemonJson, Pokemon.class);
        showDetail(pokemon);
    }

    private void showDetail(Pokemon pokemon) {
        txtDetail.setText(pokemon.getName());
    }
}
