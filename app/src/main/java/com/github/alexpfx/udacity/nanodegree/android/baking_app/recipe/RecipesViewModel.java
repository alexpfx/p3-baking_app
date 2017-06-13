package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;

import java.util.List;

/**
 * Created by alexandre on 25/05/2017.
 */
public class RecipesViewModel extends AndroidViewModel {
    private LiveData<List<Recipe>> recipes = null;
    private RecipesRepository <LiveData> mRepository;
    private static final String TAG = "RecipesViewModel";

    private Handler mHandler = new Handler(getApplication().getMainLooper());

    public RecipesViewModel(Application application, @NonNull RecipesRepository repository) {
        super(application);
        mRepository = repository;
    }

    public final void loadAll() {
        recipes = mRepository.getRecipes();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return recipes;
    }
}
