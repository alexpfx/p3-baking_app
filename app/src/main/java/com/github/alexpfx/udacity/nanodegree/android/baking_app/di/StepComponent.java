package com.github.alexpfx.udacity.nanodegree.android.baking_app.di;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.detail.StepViewFragment;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.steps.StepListFragment;

import dagger.Component;

/**
 * Created by alexandre on 02/07/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface StepComponent extends ActivityComponent{
//    void inject (StepMasterActivity stepMasterActivity);
    void inject (StepListFragment stepListFragment);
    void inject (StepViewFragment stepViewFragment);
}
