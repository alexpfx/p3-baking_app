package com.github.alexpfx.udacity.nanodegree.android.baking_app.data.remote;

import android.util.Log;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by alexandre on 23/05/2017.
 */

public class RecipeServiceImpl implements RecipeService{

    private final Endpoints mEndpoints;
    private static final String URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    private static final String TAG = "RecipeServiceImpl";
    @Inject
    public RecipeServiceImpl(Retrofit retrofit) {
        Log.d(TAG, "RecipeServiceImpl: ");
        mEndpoints = retrofit.create(Endpoints.class);

    }

    @Override
    public Call<List<Recipe>> getAllRecipes() {
        return mEndpoints.getAllRecipes(URL);
    }

    private interface Endpoints {
        @GET
        Call<List<Recipe>> getAllRecipes(@Url String url);
    }


}
