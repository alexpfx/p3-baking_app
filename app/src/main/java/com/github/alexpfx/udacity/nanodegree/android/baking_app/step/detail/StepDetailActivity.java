package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;

/**
 * Created by alexandre on 06/06/2017.
 */

public class StepDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        if (savedInstanceState == null){
            StepViewFragment fragment = (StepViewFragment) getSupportFragmentManager().findFragmentById(R.id.step_view_fragment);
            int step_id = getIntent().getIntExtra("step_id", 0);
            int recipe_id = getIntent().getIntExtra("recipe_id", 0);

            fragment.init(step_id, recipe_id);
        }

    }
}
