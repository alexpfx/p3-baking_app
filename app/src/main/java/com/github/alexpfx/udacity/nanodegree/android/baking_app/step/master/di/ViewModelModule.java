package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.RepositoryModule;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.RecipesRepository;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.ingredients.IngredientsViewModel;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.steps.StepListFragment;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.steps.StepsViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandre on 18/06/2017.
 */

@Module(includes = RepositoryModule.class)
public class ViewModelModule {

    private final StepListFragment stepListFragment;

    public ViewModelModule(StepListFragment stepListFragment) {
        this.stepListFragment = stepListFragment;
    }

    @Provides
    StepListFragment stepListFragment (){
        return stepListFragment;
    }

    @Provides
    IngredientsViewModel ingredientsViewModel(StepListFragment stepListFragment, RecipesRepository repository) {
        return ViewModelProviders.of(stepListFragment, new ViewModelProvider.Factory() {
            @Override
            public <T extends ViewModel> T create(Class<T> modelClass) {
                return (T) new IngredientsViewModel(repository);
            }
        }).get(IngredientsViewModel.class);
    }

    @Provides
    StepsViewModel stepsViewModel(StepListFragment stepListFragment, RecipesRepository repository) {
        return ViewModelProviders.of(stepListFragment, new ViewModelProvider.Factory() {
            @Override
            public <T extends ViewModel> T create(Class<T> modelClass) {
                return (T) new StepsViewModel(repository);
            }
        }).get(StepsViewModel.class);
    }


}
