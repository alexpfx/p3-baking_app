package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by alexandre on 25/05/2017.
 */

public class RecipesViewHolder extends BaseViewHolder<Recipe> {

    @BindView(R.id.image_background)
    ImageView imageBackground;

    @BindView(R.id.text_recipe_name)
    TextView textRecipeName;

    public RecipesViewHolder(View view, Context context) {
        super(view, context);
    }


    @Override
    public void bind(Recipe recipe) {
        setTag(recipe);
        String name = recipe.getName();
        textRecipeName.setText(name);
        View rootView = textRecipeName.getRootView();
        rootView.setTag(recipe);

        if (recipe.getImage() == null || recipe.getImage().isEmpty()) {
            int drawable = getRecipeImage(recipe);
            Glide.with(getContext()).load(drawable).asBitmap().centerCrop().into(imageBackground);
        } else {
            Glide.with(getContext()).load(recipe.getImage()).asBitmap().centerCrop().into(imageBackground);
        }
    }


    private int getRecipeImage(Recipe recipe) {
        String n = recipe.getName().toLowerCase().replaceAll("\\s", "");
        return getContext().getResources().getIdentifier(n, "drawable", getContext().getPackageName());

    }


}
