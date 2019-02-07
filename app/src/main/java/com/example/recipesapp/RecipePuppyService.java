package com.example.recipesapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipePuppyService {
    @GET("api/")
    Call<RecipesResponse> searchByIngredient(
            @Query("i") String ingredients,
            @Query("q") String query);
}
