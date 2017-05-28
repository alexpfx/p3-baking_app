package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.detail.ingredients;

import android.view.View;
import android.widget.TextView;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Ingredient;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by alexandre on 27/05/2017.
 */

public class IngredientsViewHolder extends BaseViewHolder<Ingredient> {


    @BindView(R.id.text_quantity)
    TextView textQuantity;
    @BindView(R.id.text_measure)
    TextView textMeasure;
    @BindView(R.id.text_ingredient)
    TextView textIngredient;

    public IngredientsViewHolder(View view) {
        super(view);
    }

    @Override
    public void bind(Ingredient ingredient) {
        textIngredient.setText(ingredient.getIngredient());
        textMeasure.setText(ingredient.getMeasure());
        textQuantity.setText(String.valueOf(ingredient.getQuantity()));
    }


}
