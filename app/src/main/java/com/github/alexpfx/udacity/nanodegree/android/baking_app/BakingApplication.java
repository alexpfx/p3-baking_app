package com.github.alexpfx.udacity.nanodegree.android.baking_app;

import android.app.Activity;
import android.app.Application;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.ApplicationComponent;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.DaggerApplicationComponent;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.HasComponent;

/**
 * Created by alexandre on 28/05/2017.
 */

public class BakingApplication extends Application implements HasComponent<ApplicationComponent> {

    private static final String TAG = "BakingApplication";

    public static final String DATABASE_NAME = "baking_database";
    private ApplicationComponent applicationComponent;

    public static BakingApplication get(Activity activity) {
        return (BakingApplication) activity.getApplication();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().build();
        applicationComponent.inject(this);

    }


    @Override
    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}