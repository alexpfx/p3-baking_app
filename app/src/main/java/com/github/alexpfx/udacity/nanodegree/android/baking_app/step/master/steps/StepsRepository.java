package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.steps;

import android.arch.lifecycle.LiveData;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;

import java.util.List;

/**
 * Created by alexandre on 28/05/2017.
 */

interface StepsRepository {
    LiveData<List<Step>> getAllByRecipe(Integer recipeId);

    LiveData<Step> get(int id, int recipeId);
}
