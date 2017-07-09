package com.github.alexpfx.udacity.nanodegree.android.baking_app;

import android.app.Activity;
import android.app.Application;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.ApplicationComponent;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.ApplicationModule;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.DaggerApplicationComponent;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.HasComponent;

import timber.log.Timber;

/**
 * Created by alexandre on 28/05/2017.
 */
public class BakingApplication extends Application implements HasComponent<ApplicationComponent> {

    private static final String TAG = "BakingApplication";
    private ApplicationComponent component;


    public static BakingApplication get(Activity activity) {
        return (BakingApplication) activity.getApplication();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                                              .applicationModule(new ApplicationModule(this))
                                              .build();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

    }


    @Override
    public ApplicationComponent getComponent() {
        return component;
    }
}
