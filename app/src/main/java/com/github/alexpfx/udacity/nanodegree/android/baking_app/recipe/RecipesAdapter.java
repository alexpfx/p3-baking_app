package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.BaseAdapter;

/**
 * Created by alexandre on 25/05/2017.
 */

public class RecipesAdapter extends BaseAdapter<Recipe, RecipesViewHolder> {


    private final View.OnClickListener mOnClickListener;

    public RecipesAdapter(Context context, View.OnClickListener onClickListener) {
        super(context);
        mOnClickListener = onClickListener;
    }

    @Override
    public RecipesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recipe, viewGroup, false);
        return new RecipesViewHolder(view, getContext());
    }


    @Override
    public void onBindViewHolder(RecipesViewHolder holder, int position) {
        Recipe recipe = getItemAt(position);
        holder.setOnClickListener(mOnClickListener, recipe);
        holder.bind(recipe);
    }


}
