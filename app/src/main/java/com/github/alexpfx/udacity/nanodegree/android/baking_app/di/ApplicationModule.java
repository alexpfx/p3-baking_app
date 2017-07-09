package com.github.alexpfx.udacity.nanodegree.android.baking_app.di;

import android.app.Application;
import android.content.Context;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.JobExecutor;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandre on 01/07/2017.
 */

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Context applicationContext() {
        return application;
    }

    @Provides
    @Singleton
    Executor executor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

}
