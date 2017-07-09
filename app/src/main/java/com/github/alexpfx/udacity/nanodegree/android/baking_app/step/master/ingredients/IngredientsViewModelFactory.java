package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.ingredients;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.PerActivity;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RecipesRepository;

import javax.inject.Inject;

/**
 * Created by alexandre on 06/07/2017.
 */

@PerActivity
public class IngredientsViewModelFactory implements ViewModelProvider.Factory {
    private RecipesRepository recipesRepository;

    @Inject
    public IngredientsViewModelFactory(RecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new IngredientsViewModel(recipesRepository);
    }
}
