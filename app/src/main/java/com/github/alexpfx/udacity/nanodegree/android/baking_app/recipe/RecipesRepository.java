package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe;

import android.arch.lifecycle.LiveData;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Ingredient;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;

import java.util.List;

/**
 * Created by alexandre on 07/06/2017.
 */

public interface RecipesRepository {
    LiveData<List<Recipe>> getRecipes();

    LiveData<List<Ingredient>> getIngredientsByRecipeId(int recipeId);

    LiveData<List<Step>> getStepsByRecipeId(int recipeId);

    LiveData<Step> getStep(int stepId, int recipeId);

    LiveData<Ingredient> getIngredient(int ingredientId, int recipeId);


}
