package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe;

import android.arch.lifecycle.LiveData;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;

import java.util.List;

/**
 * Created by alexandre on 16/06/2017.
 */

public interface RecipeDataSource {
    void save(List<Recipe> recipes);
    LiveData<List<Recipe>> getRecipes ();
    boolean hasData ();
}
