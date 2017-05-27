package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexandre on 25/05/2017.
 */

public class RecipesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.image_background)
    ImageView imageBackground;

    @BindView(R.id.text_recipe_name)
    TextView textRecipeName;

    public RecipesViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);

    }





}
