package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.list;

import android.arch.lifecycle.LiveData;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.BakingAppDatabase;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;

import java.util.List;

/**
 * Created by alexandre on 27/05/2017.
 */

class RecipesRepositoryImpl implements RecipesRepository {

    BakingAppDatabase mDatabase;

    public RecipesRepositoryImpl(BakingAppDatabase database) {
        mDatabase = database;
    }

    @Override
    public LiveData<List<Recipe>> getRecipes() {
        return mDatabase.recipeDao().getAll();
    }
}
