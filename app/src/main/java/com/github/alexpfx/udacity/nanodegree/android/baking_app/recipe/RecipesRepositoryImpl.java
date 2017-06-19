package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe;

import android.arch.lifecycle.LiveData;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Ingredient;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;
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
    public LiveData<List<Recipe>> getRecipes() {
        refresh();

        return dataSource.getRecipes();
    }

    @Override
    public LiveData<List<Ingredient>> getIngredientsByRecipeId(int recipeId) {
        refresh();

        return dataSource.getIngredientsByRecipeId(recipeId);
    }

    @Override
    public LiveData<List<Step>> getStepsByRecipeId(int recipeId) {
        refresh();

        return dataSource.getStepsByRecipeId(recipeId);
    }

    @Override
    public LiveData<Step> getStep(int recipeId, int stepId) {
        return null;
    }

    @Override
    public LiveData<Ingredient> getIngredient(int recipeId, int ingredientId) {
        return null;
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




