package com.github.tancan21.project.data;

import com.github.tancan21.project.presentation.model.RestPokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemonApi {

   @GET("api/v2/pokemon")
   Call<RestPokemonResponse> getPokemonResponse() ;
}
