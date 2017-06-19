package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.steps;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RecipesRepository;

import java.util.List;

/**
 * Created by alexandre on 28/05/2017.
 */

public class StepsViewModel extends ViewModel {

    private LiveData<List<Step>> stepsByRecipe;
    private LiveData<Step> step;
    private RecipesRepository mRepository;


    public StepsViewModel(@NonNull RecipesRepository repository) {
        mRepository = repository;
    }

    public void loadAllByRecipeId(Integer... params) {
        if (stepsByRecipe != null) {
            return;
        }
        stepsByRecipe = mRepository.getStepsByRecipeId(params[0]);

    }

    public void load(int id, int recipeId) {
        step = mRepository.getStep(id, recipeId);
    }

    public LiveData<Step> getStep() {
        return step;
    }

    public LiveData<List<Step>> getStepsByRecipe() {
        return stepsByRecipe;
    }
}
