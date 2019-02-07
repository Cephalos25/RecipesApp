package com.example.recipesapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends ArrayAdapter<Recipe> {
    private Context context;
    private int resource;
    private List<Recipe> recipes;

    public RecipeAdapter(@NonNull Context context, int resource, @NonNull List<Recipe> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.recipes = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recipelist, parent);
        }

        TextView nameView = convertView.findViewById(R.id.textView_listitem_name);
        ImageView thumbnail = convertView.findViewById(R.id.imageView_listitem_thumbnail);

        Recipe currentRecipe = recipes.get(position);
        nameView.setText(currentRecipe.getTitle());
        Picasso.get().load(currentRecipe.getThumbnail()).into(thumbnail);

        return super.getView(position, convertView, parent);
    }
}
