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


    @BindView(R.id.text_ingredient)
    TextView textIngredient;

    public IngredientsViewHolder(View view) {
        super(view);
    }

    @Override
    public void bind(Ingredient ingredient) {
        double quantity = ingredient.getQuantity();
        String text = getFormatedText(ingredient, quantity);
        textIngredient.setText(text);
    }

    private String getFormatedText(Ingredient ingredient, double quantity) {
        if (quantity == (long) quantity) {
            return String.format("%s %s - %s", String.valueOf(quantity), ingredient.getMeasure(), ingredient.getIngredient());
        } else {
            return String.format("%.1f %s - %s", quantity, ingredient.getMeasure(), ingredient.getIngredient());
        }
    }


}
