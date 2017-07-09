package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.steps;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RecipesRepository;

import javax.inject.Inject;

public class StepViewModelFactory implements ViewModelProvider.Factory {
    private RecipesRepository recipesRepository;

    @Inject
    public StepViewModelFactory(RecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new StepsViewModel(recipesRepository);
    }
}
