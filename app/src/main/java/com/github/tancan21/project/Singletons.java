package com.github.tancan21.project;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.tancan21.project.data.PokemonApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static  Gson gsonInstance;
    private static  PokemonApi pokemonApiInstance;
    private static SharedPreferences sharedPreferencesIntance;

    public static Gson getGson()
    {
        if (gsonInstance == null)
        {
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }

    public static PokemonApi getPokemonAPi(){
        if (pokemonApiInstance == null)
        {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create(getGson())).build();
            pokemonApiInstance = retrofit.create(PokemonApi.class);
        }
        return pokemonApiInstance;
    }

    public static SharedPreferences getSharedPreferences(Context context)
    {
        if (sharedPreferencesIntance == null)
        {
            sharedPreferencesIntance = context.getSharedPreferences(Constants.APPLICATION_KADIOGLU, Context.MODE_PRIVATE);
        }
        return sharedPreferencesIntance;
    }
}

