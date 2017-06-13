package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe;

/**
 * Created by alexandre on 07/06/2017.
 */

public interface WritableDataSource <T> {
    void saveRecipes(T data);

}
