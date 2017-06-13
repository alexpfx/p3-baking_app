package com.github.alexpfx.udacity.nanodegree.android.baking_app.base;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.BakingAppDatabase;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.remote.RecipeService;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.LocalDataSource;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RecipesRepositoryImpl;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RemoteDataSource;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RemoteUpdatable;

/**
 * Created by alexandre on 28/05/2017.
 */

public class BaseApplication extends Application {

    public static final String DATABASE_NAME = "baking_database";

    RemoteUpdatable mRemoteUpdatable;

    @Override
    public void onCreate() {
        super.onCreate();
        BakingAppDatabase database =
                Room.databaseBuilder(this, BakingAppDatabase.class, "baking_database").build();
        mRemoteUpdatable = new RecipesRepositoryImpl(new LocalDataSource(database), new RemoteDataSource(new RecipeService()));
        mRemoteUpdatable.loadRemoteData();
    }
}
