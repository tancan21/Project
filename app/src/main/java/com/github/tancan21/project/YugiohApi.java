package com.github.tancan21.project;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YugiohApi {

   @GET("api/v7/cardinfo.php")
   Call<RestYugiohResponse> getYugiohResponse() ;
}
