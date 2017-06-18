package com.github.alexpfx.udacity.nanodegree.android.baking_app.di;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandre on 16/06/2017.
 */

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application application() {
        return application;
    }




}
