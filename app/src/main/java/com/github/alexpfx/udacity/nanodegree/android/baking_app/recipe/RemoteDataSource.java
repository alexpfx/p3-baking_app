package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.remote.RecipeService;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by alexandre on 08/06/2017.
 */

public class RemoteDataSource implements RecipesDataSource<Flowable<List<Recipe>>> {

    private static final String TAG = "RemoteDataSource";
    private RecipeService mRecipeService;

    public RemoteDataSource(RecipeService recipeService) {
        mRecipeService = recipeService;
    }


    @Override
    public Flowable<List<Recipe>> getRecipes() {
        return mRecipeService.getAllRecipes();
    }
}
