package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.steps;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.BaseViewModel;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;

import java.util.List;

/**
 * Created by alexandre on 28/05/2017.
 */

public class StepsViewModel extends BaseViewModel<StepsRepository, Integer> {

    private LiveData<List<Step>> stepsByRecipe;
    private LiveData<Step> step;


    public StepsViewModel(@NonNull StepsRepository repository) {
        super(repository);
    }

    public void loadAllByRecipeId(Integer... params) {
        if (stepsByRecipe != null) {
            return;
        }
        stepsByRecipe = getRepository().getAllByRecipe(params[0]);

    }

    public void load (int id, int recipeId){
        step = getRepository().get (id, recipeId);
    }

    public LiveData<Step> getStep() {
        return step;
    }

    public LiveData<List<Step>> getStepsByRecipe() {
        return stepsByRecipe;
    }
}
