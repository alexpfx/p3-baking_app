package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;

import java.util.List;

/**
 * Created by alexandre on 25/05/2017.
 */
public class RecipesViewModel extends AndroidViewModel {
    private static final String TAG = "RecipesViewModel";
    private final RecipesRepository recipesRepository;

    private LiveData<List<Recipe>> recipes = null;

    public RecipesViewModel(Application application, RecipesRepository recipesRepository) {
        super(application);
        this.recipesRepository = recipesRepository;
    }

    public final void loadAll() {
        recipes = recipesRepository.getData();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return recipes;
    }
}
