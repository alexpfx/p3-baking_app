package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.di;

import android.content.Context;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RecipeActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandre on 17/06/2017.
 */
@Module
public class RecipeActivityModule {

    private final RecipeActivity recipeActivity;

    public RecipeActivityModule(RecipeActivity recipeActivity){
        this.recipeActivity = recipeActivity;
    }

    @Provides
    Context context (){
        return recipeActivity;
    }


    @Provides
    RecipeActivity recipeActivity (){
        return this.recipeActivity;
    }

}
