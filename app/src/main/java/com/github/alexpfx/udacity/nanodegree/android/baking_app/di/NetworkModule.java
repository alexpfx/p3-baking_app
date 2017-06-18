package com.github.alexpfx.udacity.nanodegree.android.baking_app.di;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.remote.RecipeService;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.remote.RecipeServiceImpl;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexandre on 18/06/2017.
 */

@Module
public class NetworkModule {

    @Provides
    Retrofit retrofit(Executor executor){
        return new Retrofit.Builder().callbackExecutor(executor).baseUrl("https://d17h27t6h515a5.cloudfront.net")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    RecipeService getRecipeService(Retrofit retrofit) {
        return new RecipeServiceImpl(retrofit);
    }

    @Provides
    Executor executor() {
        return new ThreadPoolExecutor(4, 8, 160,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    }

}
