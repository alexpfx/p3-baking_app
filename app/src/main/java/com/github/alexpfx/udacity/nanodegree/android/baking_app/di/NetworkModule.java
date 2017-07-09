package com.github.alexpfx.udacity.nanodegree.android.baking_app.di;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.remote.RecipeService;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.remote.RecipeServiceImpl;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexandre on 02/07/2017.
 */
@Module
public class NetworkModule {
    public static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net";

    @Provides
    @Singleton
    Retrofit retrofit(Executor executor) {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                                     .addConverterFactory(
                                             GsonConverterFactory.create())
                                     .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                     .callbackExecutor(executor)
                                     .build();
    }

    @Provides
    @Singleton
    RecipeService recipeService(RecipeServiceImpl recipeService) {
        return recipeService;
    }
}
