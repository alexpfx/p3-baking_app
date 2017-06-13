package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe;

import android.arch.lifecycle.LiveData;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.BakingAppDatabase;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.IngredientDao;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.RecipeDao;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.StepDao;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Ingredient;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;

import java.util.List;

/**
 * Created by alexandre on 08/06/2017.
 */

public class LocalDataSource implements RecipesDataSource<LiveData<List<Recipe>>>, WritableDataSource<List<Recipe>> {

    private BakingAppDatabase mDatabase;


    public LocalDataSource(BakingAppDatabase database) {
        mDatabase = database;
    }

    private static final String TAG = "LocalDataSource";

    @Override
    public LiveData<List<Recipe>> getRecipes() {
        LiveData<List<Recipe>> data = mDatabase.recipeDao().getAll();
        return data;
    }

    @Override
    public void saveRecipes(List<Recipe> recipes) {
        RecipeDao recipeDao = mDatabase.recipeDao();
        IngredientDao ingredientDao = mDatabase.ingredientDao();
        StepDao stepDao = mDatabase.stepDao();
        for (Recipe recipe : recipes) {
            recipeDao.insert(recipe);
            int count = 1;

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.setId(count++);
                ingredient.setRecipeId(recipe.getId());
                ingredientDao.insert(ingredient);
            }

            for (Step step : recipe.getSteps()) {
                step.setRecipeId(recipe.getId());
                stepDao.insert(step);
            }

        }
    }
}
