package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe;

import android.arch.lifecycle.LiveData;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.RecipeDao;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;

import java.util.List;

/**
 * Created by alexandre on 27/05/2017.
 */

public class RecipesRepositoryImpl implements RecipesRepository {

    RecipeDao mRecipeDao;

    public RecipesRepositoryImpl(RecipeDao recipeDao) {
        mRecipeDao = recipeDao;
    }

    @Override
    public LiveData<List<Recipe>> getRecipes() {
        return mRecipeDao.getAll();
    }
}
