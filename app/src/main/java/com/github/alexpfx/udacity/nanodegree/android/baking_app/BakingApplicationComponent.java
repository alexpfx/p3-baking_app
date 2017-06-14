package com.github.alexpfx.udacity.nanodegree.android.baking_app;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.BakingAppDatabase;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RemoteUpdatable;

import dagger.Component;

/**
 * Created by alexandre on 13/06/2017.
 */
@Component(modules = {DataModule.class})
public interface BakingApplicationComponent {

    BakingAppDatabase getBakingAppDatabase ();

    RemoteUpdatable getRemoteUpdatable ();

}
