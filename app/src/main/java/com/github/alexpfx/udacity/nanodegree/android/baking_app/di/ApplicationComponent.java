package com.github.alexpfx.udacity.nanodegree.android.baking_app.di;

import android.content.Context;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RecipesRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alexandre on 01/07/2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class, RepositoryModule.class})
public interface ApplicationComponent {
    Context getApplicationContext();
    RecipesRepository recipesRepository ();

}
