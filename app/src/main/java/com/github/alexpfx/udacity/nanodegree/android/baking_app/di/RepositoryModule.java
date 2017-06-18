package com.github.alexpfx.udacity.nanodegree.android.baking_app.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.BakingAppDatabase;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.remote.RecipeService;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RecipeDataSource;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RecipeLocalDataSource;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RecipesRepository;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RecipesRepositoryImpl;

import java.util.concurrent.Executor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandre on 17/06/2017.
 */

@Module(includes = {ApplicationModule.class, NetworkModule.class})
public class RepositoryModule {

    @Provides
    RecipesRepository recipesRepository(RecipeService recipeService, Executor executor, RecipeDataSource dataSource) {
        return new RecipesRepositoryImpl(recipeService, executor, dataSource);
    }


    @Provides
    public RecipeDataSource recipeDataSource(BakingAppDatabase database) {
        return new RecipeLocalDataSource(database.recipeDao(), database.stepDao(), database.ingredientDao());
    }

    @Provides
    public BakingAppDatabase bakingAppDatabase(Application context) {
        return Room.databaseBuilder(context, BakingAppDatabase.class, "baking_db").build();
    }



}
