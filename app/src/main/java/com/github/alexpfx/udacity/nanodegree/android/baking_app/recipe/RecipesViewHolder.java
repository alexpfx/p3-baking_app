package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexandre on 25/05/2017.
 */

public class RecipesViewHolder extends RecyclerView.ViewHolder {

    private final Context mContext;
    @BindView(R.id.image_background)
    ImageView imageBackground;

    @BindView(R.id.text_recipe_name)
    TextView textRecipeName;

    @Inject
    public RecipesViewHolder(View view, Context context) {
        super(view);
        ButterKnife.bind(this, view);

        mContext = context;
    }


    public void bind(Recipe recipe) {
        String name = recipe.getName();
        textRecipeName.setText(name);

        if (recipe.getImage() == null || recipe.getImage()
                                               .isEmpty()) {
            int drawable = getRecipeImage(recipe);
            Glide.with(mContext)
                 .load(drawable)
                 .asBitmap()
                 .centerCrop()
                 .into(imageBackground);
        } else {
            Glide.with(mContext)
                 .load(recipe.getImage())
                 .asBitmap()
                 .centerCrop()
                 .into(imageBackground);
        }
        itemView.setTag(recipe);
    }

    private int getRecipeImage(Recipe recipe) {
        String n = recipe.getName()
                         .toLowerCase()
                         .replaceAll("\\s", "");

        return mContext.getResources()
                       .getIdentifier(n, "drawable", mContext.getPackageName());

    }

}
