package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe;

import android.arch.lifecycle.LiveData;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by alexandre on 27/05/2017.
 */

public class RecipesRepositoryImpl implements RecipesRepository, RemoteUpdatable {

    private RecipesDataSource<LiveData> mLocal;
    private RecipesDataSource<Flowable> mRemote;


    public RecipesRepositoryImpl(RecipesDataSource local, RecipesDataSource remote) {
        mLocal = local;
        mRemote = remote;
    }

    public RecipesRepositoryImpl(RecipesDataSource local) {
        mLocal = local;
    }

    @Override
    public LiveData<List<Recipe>> getRecipes() {
        return mLocal.getRecipes();
    }

    @Override
    public void loadRemoteData() {
        mRemote.getRecipes().subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(data -> {
            WritableDataSource<List<Recipe>> wDataSource = (WritableDataSource<List<Recipe>>) mLocal;
            List<Recipe> recipes = (List<Recipe>) data;
            wDataSource.saveRecipes(recipes);



        });
    }
}
