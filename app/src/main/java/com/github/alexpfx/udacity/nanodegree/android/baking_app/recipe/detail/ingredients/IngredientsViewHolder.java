package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.detail.ingredients;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexandre on 27/05/2017.
 */

public class IngredientsViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.text_quantity)
    TextView textQuantity;
    @BindView(R.id.text_measure)
    TextView textMeasure;
    @BindView(R.id.text_ingredient)
    TextView textIngredient;

    public IngredientsViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }


}
