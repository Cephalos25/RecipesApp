package com.example.recipesapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "com.example.recipesapp";

    RecipePuppyService recipePuppyService;

    private EditText editIngredients;
    private EditText editQuery;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildInternals();
        wireWidgets();
        setListeners();
        searchRecipes();
    }

    private void setListeners() {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<RecipesResponse> recipesResponseCall = recipePuppyService
                        .searchByIngredient(editIngredients.getText().toString(),
                                editQuery.getText().toString());
                recipesResponseCall.enqueue(new Callback<RecipesResponse>() {
                    @Override
                    public void onResponse(Call<RecipesResponse> call, Response<RecipesResponse> response) {
                        RecipesResponse result = response.body();
                        List<Recipe> recipes = null;
                        if (result != null) {
                            recipes = result.getResults();
                            Log.d(TAG, recipes.toString());
                            Intent viewRecipesIntent = new Intent(MainActivity.this,
                                    RecipeListActivity.class);
                            viewRecipesIntent.putParcelableArrayListExtra("recipes",
                                    new ArrayList<>(recipes));
                        } else {
                            Log.d(TAG, "No results");
                        }
                    }

                    @Override
                    public void onFailure(Call<RecipesResponse> call, Throwable t) {
                        Log.d(TAG, "There's an error", t);
                    }
                });
            }
        });
    }

    private void wireWidgets() {
        editIngredients = findViewById(R.id.editText_main_ingredients);
        editQuery = findViewById(R.id.editText_main_query);
        searchButton = findViewById(R.id.button_main_search);
    }

    private void searchRecipes() {
        //temporarily hardcode query
        // TODO make edittexts
        Call<RecipesResponse> recipesResponseCall = recipePuppyService
                .searchByIngredient("chocolate", "eggs");
        recipesResponseCall.enqueue(new Callback<RecipesResponse>() {
            @Override
            public void onResponse(Call<RecipesResponse> call, Response<RecipesResponse> response) {
                RecipesResponse result = response.body();
                List<Recipe> recipes = null;
                if (result != null) {
                    recipes = result.getResults();
                    Log.d(TAG, recipes.toString());
                } else {
                    Log.d(TAG, "No results");
                }
            }

            @Override
            public void onFailure(Call<RecipesResponse> call, Throwable t) {
                Log.d(TAG, "There's an error", t);
            }
        });
    }

    private void buildInternals() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://recipepuppy.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        recipePuppyService = retrofit.create(RecipePuppyService.class);
    }
}
