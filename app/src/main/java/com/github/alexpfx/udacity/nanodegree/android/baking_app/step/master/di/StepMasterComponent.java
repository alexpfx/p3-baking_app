package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.di;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.ContextModule;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.RepositoryModule;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.steps.StepListFragment;

import dagger.Component;

/**
 * Created by alexandre on 18/06/2017.
 */

@Component(modules = {RepositoryModule.class, ContextModule.class, ViewModelModule.class})
public interface StepMasterComponent {
    void inject (StepListFragment stepListFragment);
}
