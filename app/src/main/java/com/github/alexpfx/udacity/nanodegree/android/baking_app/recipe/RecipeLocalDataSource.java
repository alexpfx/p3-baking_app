package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe;

import android.arch.lifecycle.LiveData;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.IngredientDao;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.RecipeDao;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.StepDao;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Ingredient;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;

import java.util.List;

/**
 * Created by alexandre on 16/06/2017.
 */

public class RecipeLocalDataSource implements RecipeDataSource {

    private IngredientDao ingredientDao;
    private RecipeDao recipeDao;
    private StepDao stepDao;

    public RecipeLocalDataSource(RecipeDao recipeDao, StepDao stepDao, IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
        this.recipeDao = recipeDao;
        this.stepDao = stepDao;
    }


    @Override
    public void save(List<Recipe> recipes) {
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

    @Override
    public LiveData<List<Recipe>> getRecipes() {
        return recipeDao.getAll();
    }

    @Override
    public boolean hasData() {
        return recipeDao.hasData();
    }


}
