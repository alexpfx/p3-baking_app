package com.github.alexpfx.udacity.nanodegree.android.baking_app.step;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.SharedViewModel;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.ActivityModule;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.ApplicationComponent;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.DaggerStepComponent;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.HasComponent;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.StepComponent;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.detail.StepViewFragment;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.steps.StepListFragment;

import timber.log.Timber;


public class StepMasterActivity extends AppCompatActivity implements LifecycleRegistryOwner, SharedViewModel.OnSelectListener<Step>, HasComponent<StepComponent> {

    public static final String KEY_RECIPE_ID = "RECIPE_ID";

    private static final String TAG = "StepActivity";
    private boolean isTablet;

    private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);

    SharedViewModel<String> recipeIdSharedViewModel;
    private StepComponent stepComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_master);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        isTablet = getResources().getBoolean(R.bool.is_tablet);

        stepComponent = DaggerStepComponent.builder()
                           .applicationComponent(((HasComponent<ApplicationComponent>) getApplication()).getComponent())
                           .activityModule(new ActivityModule(this)).build();

        if (savedInstanceState == null) {
            String recipeId = getIntent().getStringExtra(KEY_RECIPE_ID);

            SharedViewModel step = ViewModelProviders.of(this)
                                                     .get("step", SharedViewModel.class);
            step.setListener(this);

            recipeIdSharedViewModel = ViewModelProviders.of(this)
                                                        .get("recipe", SharedViewModel.class);
            recipeIdSharedViewModel.select(recipeId);


            if (isTablet) {
                Timber.i("two pane");

            } else {
                Timber.i("one pane");
                StepListFragment stepListFragment = new StepListFragment();
                getSupportFragmentManager().beginTransaction()
                                           .replace(R.id.step_master_container, stepListFragment)
                                           .commit();
            }
        }

    }


    @Override
    public LifecycleRegistry getLifecycle() {
        return mRegistry;
    }

    @Override
    public void selected(Step item) {
        Timber.i("step selected: %s", item.getId());
        getSupportFragmentManager().beginTransaction()
                                   .addToBackStack(null)
                                   .replace(R.id.step_master_container, new StepViewFragment())
                                   .commit();
    }





    @Override
    public StepComponent getComponent() {
        return stepComponent;
    }
}
