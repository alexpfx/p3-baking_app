package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;

import java.util.List;

/**
 * Created by alexandre on 25/05/2017.
 */
public class RecipesViewModel extends ViewModel {
    private static final String TAG = "RecipesViewModel";
    private final RecipesRepository recipesRepository;

    private LiveData<List<Recipe>> recipes = null;

    public RecipesViewModel(RecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    public final void loadAll() {
        recipes = recipesRepository.getRecipes();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return recipes;
    }
}
