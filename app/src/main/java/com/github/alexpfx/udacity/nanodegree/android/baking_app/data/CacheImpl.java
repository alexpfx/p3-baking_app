package com.github.alexpfx.udacity.nanodegree.android.baking_app.data;

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

                for (Ingredient ingredient : recipe.getIngredients()) {
                    mLocal.ingredientDao().insert(ingredient);
                }

                for (Step step : recipe.getSteps()) {
                    mLocal.stepDao().insert(step);
                }

            }
        });
    }
}
