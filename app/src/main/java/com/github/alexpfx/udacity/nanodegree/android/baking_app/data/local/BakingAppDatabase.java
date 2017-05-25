package com.github.alexpfx.udacity.nanodegree.android.baking_app.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.Ingredient;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.IngredientDao;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.Recipe;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.RecipeDao;

/**
 * Created by alexandre on 24/05/2017.
 */

@Database(entities = {Recipe.class,Ingredient.class, Step.class}, version = 1)
public abstract class BakingAppDatabase extends RoomDatabase {

    public abstract StepDao stepDao ();

    public abstract IngredientDao ingredientDao();

    public abstract RecipeDao recipeDao ();

}
