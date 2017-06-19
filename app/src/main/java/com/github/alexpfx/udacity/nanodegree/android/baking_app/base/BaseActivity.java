package com.github.alexpfx.udacity.nanodegree.android.baking_app.base;

import android.support.v7.app.AppCompatActivity;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.ApplicationComponent;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.HasComponent;

/**
 * Created by alexandre on 18/06/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public ApplicationComponent getApplicationComponent() {
        return ((HasComponent<ApplicationComponent>) getApplication()).getComponent();
    }

}
