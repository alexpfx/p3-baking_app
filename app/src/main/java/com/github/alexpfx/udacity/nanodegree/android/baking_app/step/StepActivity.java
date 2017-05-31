package com.github.alexpfx.udacity.nanodegree.android.baking_app.step;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.detail.StepViewFragment;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.steps.StepListFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class StepActivity extends AppCompatActivity {

    public static final String KEY_RECIPE_ID = "RECIPE_ID";

    private static final String TAG = "StepActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (savedInstanceState == null) {
            String recipeId = getIntent().getStringExtra(KEY_RECIPE_ID);
            Log.d(TAG, "onCreate: " + recipeId);
            StepListFragment fragment = StepListFragment.newInstance(recipeId);
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_main_content, fragment).commit();
        }

    }


    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowStepViewMessage(Object o) {
        Log.d(TAG, "onShowStepViewMessage: ");
        getSupportFragmentManager().beginTransaction().replace(R.id.detail_main_content, new StepViewFragment()).commit();
    }


}
