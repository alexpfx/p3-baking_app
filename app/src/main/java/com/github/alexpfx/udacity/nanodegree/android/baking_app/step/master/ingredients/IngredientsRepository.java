package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.ingredients;

import android.arch.lifecycle.LiveData;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Ingredient;

import java.util.List;

/**
 * Created by alexandre on 28/05/2017.
 */

public interface IngredientsRepository {


    LiveData<List<Ingredient>> getAllByRecipeId(int recipeId);
}
