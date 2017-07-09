package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.ingredients;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Ingredient;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RecipesRepository;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by alexandre on 28/05/2017.
 */

public class IngredientsViewModel extends ViewModel {

    LiveData<List<Ingredient>> ingredientsByRecipe = null;
    private RecipesRepository mRepository;

    @Inject
    public IngredientsViewModel(@NonNull RecipesRepository repository) {
        mRepository = repository;
    }

    public final void loadAllByRecipeId(@NonNull Integer... recipeId) {
        Integer rid = recipeId[0];
        Timber.i("loading ingredients for recipe: %s", rid);
        ingredientsByRecipe = mRepository.getIngredientsByRecipeId(rid);
    }

    public LiveData<List<Ingredient>> getIngredientsByRecipe() {
        return ingredientsByRecipe;
    }



}


