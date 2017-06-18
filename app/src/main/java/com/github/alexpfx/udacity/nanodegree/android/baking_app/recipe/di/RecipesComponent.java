package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.di;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.ApplicationComponent;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.RepositoryModule;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RecipeActivity;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RecipesFragment;

import dagger.Component;

/**
 * Created by alexandre on 17/06/2017.
 */
@Component(dependencies = {ApplicationComponent.class}, modules = {RecipeActivityModule.class, RecipesFragmentModule.class, RepositoryModule.class})
public interface RecipesComponent {
    void inject(RecipeActivity activity);
    void inject(RecipesFragment fragment);
}
