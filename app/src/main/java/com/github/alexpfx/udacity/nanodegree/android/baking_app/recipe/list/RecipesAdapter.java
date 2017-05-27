package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 25/05/2017.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesViewHolder> {

    private List<Recipe> recipes = new ArrayList<>();

    private Context mContext;
    private View.OnClickListener mClickListener;

    public RecipesAdapter(Context context, View.OnClickListener clickListener) {

        mContext = context;
        mClickListener = clickListener;
    }

    @Override
    public RecipesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recipe, viewGroup, false);
        return new RecipesViewHolder(view);
    }

    private static final String TAG = "RecipesAdapter";

    @Override
    public void onBindViewHolder(RecipesViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        String name = recipe.getName();
        holder.textRecipeName.setText(name);
        View rootView = holder.textRecipeName.getRootView();
        rootView.setTag(recipe);
        rootView.setOnClickListener(mClickListener);

        if (recipe.getImage() == null || recipe.getImage().isEmpty()) {
            int drawable = getRecipeImage(recipe);
            Glide.with(mContext).load(drawable).asBitmap().centerCrop().into(holder.imageBackground);
        } else {
            Glide.with(mContext).load(recipe.getImage()).asBitmap().centerCrop().into(holder.imageBackground);
        }
    }

    private int getRecipeImage(Recipe recipe) {
        String n = recipe.getName().toLowerCase().replaceAll("\\s", "");
        return mContext.getResources().getIdentifier(n, "drawable", mContext.getPackageName());

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }


    public void swapRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

}
