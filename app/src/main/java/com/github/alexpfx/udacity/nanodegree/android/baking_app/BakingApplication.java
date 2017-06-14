package com.github.alexpfx.udacity.nanodegree.android.baking_app;

import android.app.Application;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RemoteUpdatable;

/**
 * Created by alexandre on 28/05/2017.
 */

public class BakingApplication extends Application {

    public static final String DATABASE_NAME = "baking_database";


    @Override
    public void onCreate() {
        super.onCreate();

        BakingApplicationComponent component = DaggerBakingApplicationComponent.builder()
                .contextModule(new ContextModule(this)).build();

        RemoteUpdatable remoteUpdatable = component.getRemoteUpdatable();
        remoteUpdatable.loadRemoteData();
    }
}
