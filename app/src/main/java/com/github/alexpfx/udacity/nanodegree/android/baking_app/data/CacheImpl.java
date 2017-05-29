package com.github.alexpfx.udacity.nanodegree.android.baking_app.data;

import android.util.Log;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.BakingAppDatabase;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Ingredient;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.remote.RecipeService;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by alexandre on 26/05/2017.
 */

public class CacheImpl implements Cache {


    private static final String TAG = "CacheImpl";
    private final RecipeService mRemote;
    private final BakingAppDatabase mLocal;

    public CacheImpl(RecipeService remote, BakingAppDatabase local) {

        mRemote = remote;
        mLocal = local;
    }

    @Override
    public void update() {
        Flowable<Recipe[]> flowable = mRemote.getAllRecipes();
        updateDatabase (flowable);
    }

    private void updateDatabase(Flowable<Recipe[]> flowable) {
        flowable.subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(recipes -> {
            for (Recipe recipe : recipes) {
                mLocal.recipeDao().insert(recipe);
                int count = 1;
                for (Ingredient ingredient : recipe.getIngredients()) {
                    ingredient.setId(count++);
                    ingredient.setRecipeId(recipe.getId());
                    mLocal.ingredientDao().insert(ingredient);
                }

                Log.d(TAG, "updateDatabase: ");
                for (Step step : recipe.getSteps()) {
                    Log.d(TAG, "updateDatabase: "+step);
                    step.setRecipeId(recipe.getId());
                    mLocal.stepDao().insert(step);
                }

            }
        });
    }
}
