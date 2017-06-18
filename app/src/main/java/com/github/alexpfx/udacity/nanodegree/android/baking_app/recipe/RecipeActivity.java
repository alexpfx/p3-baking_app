package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.ApplicationComponent;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.ApplicationModule;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.HasComponent;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.di.DaggerRecipesComponent;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.di.RecipeActivityModule;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.di.RecipesComponent;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.di.RecipesFragmentModule;

public class RecipeActivity extends AppCompatActivity implements HasComponent<RecipesComponent>{

    private RecipesComponent recipesComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recipesComponent = DaggerRecipesComponent.builder()
                .applicationComponent(getApplicationComponent())
                .applicationModule(new ApplicationModule(getApplication()))
                .recipeActivityModule(new RecipeActivityModule(this))
                .recipesFragmentModule(new RecipesFragmentModule())
                .build();

    }

    private ApplicationComponent getApplicationComponent() {
        HasComponent<ApplicationComponent> c = (HasComponent<ApplicationComponent>) getApplication();
        return c.getComponent();
    }

    @Override
    public RecipesComponent getComponent() {
        return recipesComponent;
    }
}
