package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.detail.steps;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.BaseViewModel;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;

import java.util.List;

/**
 * Created by alexandre on 28/05/2017.
 */

public class StepsViewModel extends BaseViewModel<StepsRepository, Integer> {

    private LiveData<List<Step>> stepsByRecipe;

    public StepsViewModel(@NonNull StepsRepository repository) {
        super(repository);
    }


    @Override
    public void initialize(Integer... params) {
        if (stepsByRecipe != null){
            return;
        }
        stepsByRecipe = getRepository().getAllByRecipe(params[0]);
    }

    public LiveData<List<Step>> getStepsByRecipe() {
        return stepsByRecipe;
    }
}
