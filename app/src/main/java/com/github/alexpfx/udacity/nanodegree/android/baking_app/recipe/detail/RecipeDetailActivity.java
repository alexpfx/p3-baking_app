package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.detail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;

public class RecipeDetailActivity extends AppCompatActivity implements StepIngredientsFragment.OnStepClickListener {

    public static final String KEY_RECIPE_ID = "RECIPE_ID";

    private static final String TAG = "RecipeDetailActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        if (savedInstanceState == null) {
            String recipeId = getIntent().getStringExtra(KEY_RECIPE_ID);
            Log.d(TAG, "onCreate: "+recipeId);
            StepIngredientsFragment fragment = StepIngredientsFragment.newInstance(recipeId);
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_main_content, fragment).commit();
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onStepClick(Step step) {
        Toast.makeText(this, "Step click"+step, Toast.LENGTH_SHORT).show();
    }
}
