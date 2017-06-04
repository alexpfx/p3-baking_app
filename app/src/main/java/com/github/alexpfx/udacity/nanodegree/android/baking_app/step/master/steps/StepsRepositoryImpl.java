package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.steps;

import android.arch.lifecycle.LiveData;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.StepDao;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;

import java.util.List;

/**
 * Created by alexandre on 28/05/2017.
 */

public class StepsRepositoryImpl implements StepsRepository {

    StepDao mStepDao;

    public StepsRepositoryImpl(StepDao stepDao) {
        mStepDao = stepDao;
    }

    @Override
    public LiveData<List<Step>> getAllByRecipe (Integer recipeId){
        return mStepDao.getAllByRecipe(recipeId);
    }

    @Override
    public LiveData<Step> get(int stepId, int recipeId) {
        return mStepDao.get(stepId, recipeId);
    }
}
