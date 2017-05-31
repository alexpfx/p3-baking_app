package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.ingredients;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Ingredient;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.BaseAdapter;

/**
 * Created by alexandre on 27/05/2017.
 */

public class IngredientsAdapter extends BaseAdapter<Ingredient, IngredientsViewHolder> {


    public IngredientsAdapter(Context context) {
        super(context);
    }

    @Override
    public IngredientsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_ingredient, viewGroup, false);
        return new IngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientsViewHolder holder, int position) {
        Ingredient ingredient = getItemAt(position);
        holder.bind(ingredient);


    }


}
