package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.ingredients;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.BaseViewModel;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Ingredient;

import java.util.List;

/**
 * Created by alexandre on 28/05/2017.
 */

public class IngredientsViewModel extends BaseViewModel<IngredientsRepository, Integer> {

    LiveData<List<Ingredient>> ingredientsByRecipe = null;

    public IngredientsViewModel(@NonNull IngredientsRepository repository) {
        super(repository);
    }

    public final void loadAllByRecipeId(@NonNull Integer... recipeId) {
        ingredientsByRecipe = getRepository().getAllByRecipeId(recipeId[0]);
    }

    public LiveData<List<Ingredient>> getIngredientsByRecipe() {
        return ingredientsByRecipe;
    }
}
