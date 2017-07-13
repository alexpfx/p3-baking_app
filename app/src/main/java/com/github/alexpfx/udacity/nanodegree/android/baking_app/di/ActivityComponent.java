package com.github.alexpfx.udacity.nanodegree.android.baking_app.di;

import android.app.Activity;

import dagger.Component;

/**
 * Created by alexandre on 02/07/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {

    Activity activity();
}
