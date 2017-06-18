package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe;

import android.arch.lifecycle.LiveData;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.remote.RecipeService;

import java.util.List;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alexandre on 27/05/2017.
 */

public class RecipesRepositoryImpl implements RecipesRepository {


    private static final String TAG = "RecipesRepositoryImpl";

    private final RecipeService recipeService;
    private final Executor executor;

    private RecipeDataSource dataSource;

    public RecipesRepositoryImpl(RecipeService recipeService, Executor executor, RecipeDataSource dataSource) {
        this.recipeService = recipeService;
        this.executor = executor;
        this.dataSource = dataSource;
    }

    @Override
    public LiveData<List<Recipe>> getData() {
        refresh ();

        return dataSource.getRecipes();
    }

    private void refresh() {

        executor.execute(() -> {
            if (!dataSource.hasData()) {
                recipeService.getAllRecipes().enqueue(new Callback<List<Recipe>>() {
                    @Override
                    public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                        storeResponse(response);
                    }

                    @Override
                    public void onFailure(Call<List<Recipe>> call, Throwable t) {
                        throw new RuntimeException(t);
                    }
                });
            }

        });
    }

    private void storeResponse(Response<List<Recipe>> response) {
        dataSource.save(response.body());
    }
}




