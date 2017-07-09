package com.github.alexpfx.udacity.nanodegree.android.baking_app.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.BakingAppDatabase;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.IngredientDao;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.RecipeDao;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.StepDao;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RecipeDataSource;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RecipeLocalDataSource;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RecipesRepository;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RecipesRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandre on 01/07/2017.
 */

@Module(includes = {NetworkModule.class, ApplicationModule.class})
public class RepositoryModule {


    public static final String NAME = "baking_database";

    @Provides
    @Singleton
    RecipeDao recipeDao(BakingAppDatabase database) {
        return database.recipeDao();
    }

    @Provides
    @Singleton
    IngredientDao ingredientDao(BakingAppDatabase database) {
        return database.ingredientDao();
    }

    @Provides
    @Singleton
    StepDao stepDao(BakingAppDatabase database) {
        return database.stepDao();
    }


    @Provides
    @Singleton
    BakingAppDatabase bakingAppDatabase(Context applicationContext) {
        return Room.databaseBuilder(applicationContext, BakingAppDatabase.class, NAME)
                   .build();
    }

    @Provides
    @Singleton
    RecipeDataSource recipeDataSource(RecipeLocalDataSource recipeLocalDataSource) {
        return recipeLocalDataSource;
    }

    @Provides
    @Singleton
    RecipesRepository recipesRepository(RecipesRepositoryImpl repository) {
        return repository;
    }


}
