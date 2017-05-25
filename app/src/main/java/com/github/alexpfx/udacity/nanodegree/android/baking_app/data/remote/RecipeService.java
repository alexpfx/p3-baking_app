package com.github.alexpfx.udacity.nanodegree.android.baking_app.data;

import io.reactivex.Flowable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by alexandre on 23/05/2017.
 */

public class RecipeService {

    private final Endpoints mEndpoints;
    private static final String URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    public RecipeService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://d17h27t6h515a5.cloudfront.net/")
                .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        mEndpoints = retrofit.create(Endpoints.class);
    }

    public Flowable<Recipe[]> getAllRecipes() {
        return mEndpoints.getAllRecipes(URL);
    }

    interface Endpoints {
        @GET
        Flowable<Recipe[]> getAllRecipes(@Url String url);
    }


    public static void main(String[] args) {
        Flowable<Recipe[]> allRecipes = new RecipeService().getAllRecipes();

        allRecipes.subscribe(list -> {
            for (Recipe recipe : list) {
                System.out.println(recipe.getId());
            }
        });

        System.out.println(allRecipes);
    }

}
