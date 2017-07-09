package com.github.alexpfx.udacity.nanodegree.android.baking_app.di;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RecipesFragment;

import dagger.Component;

/**
 * Created by alexandre on 02/07/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface RecipeComponent extends ActivityComponent {
    void inject(RecipesFragment recipesFragment);
}
