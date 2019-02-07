package com.example.recipesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class RecipeListActivity extends AppCompatActivity {

    private TextView backButton;
    private ListView recipeList;

    private List<Recipe> receivedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        Intent receivedIntent = getIntent();
        receivedList = receivedIntent.getParcelableArrayListExtra("recipes");

        wireWidgets();
        populateViews(receivedList);
        setListeners();
    }

    private void wireWidgets() {
        backButton = findViewById(R.id.textView_recipelist_back);
        recipeList = findViewById(R.id.listView_recipelist_recipes);
    }

    private void populateViews(List<Recipe> recipes) {

    }

    private void setListeners() {

    }
}
