package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.list;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.BaseViewModel;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;

import java.util.List;

/**
 * Created by alexandre on 25/05/2017.
 */
public class RecipesViewModel extends BaseViewModel<RecipesRepository, Void> {
    private LiveData<List<Recipe>> recipes = null;

    public RecipesViewModel(@NonNull RecipesRepository repository) {
        super(repository);
    }

    @Override
    protected final void initialize(Void ... params) {
        if (recipes != null){
            return;
        }
        recipes = getRepository().getRecipes();

    }

    public LiveData<List<Recipe>> getRecipes() {
        return recipes;
    }
}
