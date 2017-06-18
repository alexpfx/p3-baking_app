package com.github.alexpfx.udacity.nanodegree.android.baking_app.data.remote;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;

import java.util.List;

import retrofit2.Call;

/**
 * Created by alexandre on 16/06/2017.
 */

public interface RecipeService {
    Call<List<Recipe>> getAllRecipes();
}
