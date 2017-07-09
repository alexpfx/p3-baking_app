package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.PerActivity;

import javax.inject.Inject;

/**
 * Created by alexandre on 05/07/2017.
 */
@PerActivity
public class RecipeViewModelFactory implements ViewModelProvider.Factory {

    private RecipesRepository repository;

    @Inject
    public RecipeViewModelFactory(RecipesRepository repository) {
        this.repository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new RecipesViewModel(repository);
    }
}
