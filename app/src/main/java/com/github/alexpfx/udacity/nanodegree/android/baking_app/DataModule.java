package com.github.alexpfx.udacity.nanodegree.android.baking_app;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.BakingAppDatabase;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.remote.RecipeService;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.LocalDataSource;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RecipesRepositoryImpl;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RemoteDataSource;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RemoteUpdatable;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandre on 13/06/2017.
 */

@Module(includes = ContextModule.class)
public class DataModule {

    @Provides
    public BakingAppDatabase getBakingAppDatabase(Context context) {
        BakingAppDatabase database =
                Room.databaseBuilder(context, BakingAppDatabase.class, "baking_database").build();
        return database;
    }

    @Provides
    public RemoteDataSource remoteDataSource(RecipeService recipeService) {
        return new RemoteDataSource(recipeService);
    }


    @Provides
    public RecipeService recipeService() {
        return new RecipeService();
    }

    @Provides
    public LocalDataSource localDataSource(BakingAppDatabase database) {
        return new LocalDataSource(database);
    }

    @Provides
    public RemoteUpdatable remoteUpdatable(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        return new RecipesRepositoryImpl(localDataSource, remoteDataSource);
    }


}
