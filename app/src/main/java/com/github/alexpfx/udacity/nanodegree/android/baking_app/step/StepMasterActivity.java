package com.github.alexpfx.udacity.nanodegree.android.baking_app.step;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.detail.StepDetailActivity;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.detail.StepViewFragment;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.steps.StepListFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class StepMasterActivity extends AppCompatActivity {

    public static final String KEY_RECIPE_ID = "RECIPE_ID";

    private static final String TAG = "StepActivity";
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_master);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTwoPane = getResources().getBoolean(R.bool.is_tablet);

        if (savedInstanceState == null) {
            String recipeId = getIntent().getStringExtra(KEY_RECIPE_ID);
            StepListFragment fragment = (StepListFragment) getSupportFragmentManager().findFragmentById(R.id.step_list_fragment);
            fragment.init(recipeId);

            if (mTwoPane) {
                StepViewFragment stepViewFragment = new StepViewFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.step_detail_container, stepViewFragment).commit();
            }
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
    public void onShowStepViewMessage(Step step) {
        Log.d(TAG, "onShowStepViewMessage: ");

        if (mTwoPane) {
            getSupportFragmentManager().beginTransaction().replace(R.id.step_detail_container, new StepViewFragment()).commit();
        } else {
            Intent intent = new Intent(getApplicationContext(), StepDetailActivity.class);
            intent.putExtra("step_id", step.getId());
            intent.putExtra("recipe_id", step.getRecipeId());
            startActivity(intent);
        }


//        getSupportFragmentManager().beginTransaction().replace(R.id.detail_main_content, new StepViewFragment()).addToBackStack(null).commit();
    }


}
