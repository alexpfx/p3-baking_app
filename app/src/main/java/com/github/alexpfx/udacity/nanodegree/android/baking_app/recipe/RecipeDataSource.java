package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe;

import android.arch.lifecycle.LiveData;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Ingredient;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;

import java.util.List;

/**
 * Created by alexandre on 16/06/2017.
 */

public interface RecipeDataSource {
    LiveData<List<Ingredient>> getIngredientsByRecipeId(int recipeId);
    LiveData<List<Step>> getStepsByRecipeId(int recipeId);
    LiveData<Step> getStepById (int stepId, int recipeId);
    LiveData<Ingredient> getIngredientById (int ingredientId, int recipeId);
    void save(List<Recipe> recipes);
    LiveData<List<Recipe>> getRecipes ();
    boolean hasData ();
}
