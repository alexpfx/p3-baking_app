package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.ApplicationModule;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.RepositoryModule;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RecipeActivity;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RecipesRepository;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RecipesViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandre on 18/06/2017.
 */

@Module(includes = {ApplicationModule.class, RepositoryModule.class, RecipeActivityModule.class})
public class RecipesFragmentModule {


    @Provides
    public RecipesViewModel recipesViewModel(RecipeActivity activity, RecipesRepository recipesRepository) {
        return new RecipesViewModel(activity.getApplication(),
                recipesRepository);
    }

    @Provides
    ViewModelProvider.Factory factory(RecipesViewModel viewModel) {
        return new ViewModelProvider.Factory() {
            @Override
            public <T extends ViewModel> T create(Class<T> modelClass) {
                return (T) viewModel;
            }
        };
    }


}
