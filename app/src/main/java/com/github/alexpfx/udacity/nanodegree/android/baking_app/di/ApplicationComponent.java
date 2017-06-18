package com.github.alexpfx.udacity.nanodegree.android.baking_app.di;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.BakingApplication;

import dagger.Component;

/**
 * Created by alexandre on 16/06/2017.
 */

@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject (BakingApplication application);
}
