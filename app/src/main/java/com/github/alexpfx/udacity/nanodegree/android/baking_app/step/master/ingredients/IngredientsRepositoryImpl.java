package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.list.ingredients;

import android.arch.lifecycle.LiveData;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.IngredientDao;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Ingredient;

import java.util.List;

/**
 * Created by alexandre on 28/05/2017.
 */

public class IngredientsRepositoryImpl implements IngredientsRepository {

    IngredientDao mIngredientDao;

    public IngredientsRepositoryImpl(IngredientDao ingredientDao) {
        mIngredientDao = ingredientDao;
    }

    @Override
    public LiveData<List<Ingredient>> getAllByRecipeId(int recipeId) {
        return mIngredientDao.getAllByRecipeId(recipeId);
    }

}
