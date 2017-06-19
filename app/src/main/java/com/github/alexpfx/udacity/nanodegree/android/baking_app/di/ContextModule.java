package com.github.alexpfx.udacity.nanodegree.android.baking_app.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandre on 18/06/2017.
 */

@Module
public class ContextModule {
    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    Context context (){
        return context;
    }
}
