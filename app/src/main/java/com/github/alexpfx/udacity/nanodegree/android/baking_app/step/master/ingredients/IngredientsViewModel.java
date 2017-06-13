package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.ingredients;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Ingredient;

import java.util.List;

/**
 * Created by alexandre on 28/05/2017.
 */

public class IngredientsViewModel extends ViewModel {

    LiveData<List<Ingredient>> ingredientsByRecipe = null;
    private IngredientsRepository mRepository;

    public IngredientsViewModel(@NonNull IngredientsRepository repository) {
        mRepository = repository;
    }

    public final void loadAllByRecipeId(@NonNull Integer... recipeId) {
        ingredientsByRecipe = mRepository.getAllByRecipeId(recipeId[0]);
    }

    public LiveData<List<Ingredient>> getIngredientsByRecipe() {
        return ingredientsByRecipe;
    }
}
