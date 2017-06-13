package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.steps;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;

import java.util.List;

/**
 * Created by alexandre on 28/05/2017.
 */

public class StepsViewModel extends ViewModel {

    private LiveData<List<Step>> stepsByRecipe;
    private LiveData<Step> step;
    private StepsRepository mRepository;


    public StepsViewModel(@NonNull StepsRepository repository) {
        mRepository = repository;
    }

    public void loadAllByRecipeId(Integer... params) {
        if (stepsByRecipe != null) {
            return;
        }
        stepsByRecipe = mRepository.getAllByRecipe(params[0]);

    }

    public void load(int id, int recipeId) {
        step = mRepository.get(id, recipeId);
    }

    public LiveData<Step> getStep() {
        return step;
    }

    public LiveData<List<Step>> getStepsByRecipe() {
        return stepsByRecipe;
    }
}
