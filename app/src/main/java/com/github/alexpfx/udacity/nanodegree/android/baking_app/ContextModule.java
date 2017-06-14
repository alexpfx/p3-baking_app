package com.github.alexpfx.udacity.nanodegree.android.baking_app;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandre on 13/06/2017.
 */

@Module
public class ContextModule {

    private final Context mContext;

    public ContextModule(Context context) {
        mContext = context;
    }

    @Provides
    public Context context(){
        return mContext;
    }
}
