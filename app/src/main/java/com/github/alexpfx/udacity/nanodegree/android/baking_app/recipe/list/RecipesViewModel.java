package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;

import java.util.List;

/**
 * Created by alexandre on 25/05/2017.
 */

public class RecipesViewModel extends ViewModel {
    RecipesRepository mRepository;

    private LiveData<List<Recipe>> recipes = new MutableLiveData<>();


    public void injectRepository(RecipesRepository repository) {
        mRepository = repository;
        recipes = repository.getRecipes();
    }

    public LiveData<List<Recipe>> getRecipes() {
        if (mRepository == null) {
            throw new NullPointerException("repository cannot be null, please inject it before call this method.");
        }
        return recipes;
    }
}
