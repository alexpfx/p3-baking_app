package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.AdapterCallback;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.ActivityModule;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.ApplicationComponent;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.DaggerRecipeComponent;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.HasComponent;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.StepMasterActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;


public class RecipesFragment extends LifecycleFragment {

    @BindView(R.id.recycler_recipes)
    RecyclerView recyclerRecipes;

    @Inject
    RecipesAdapter adapterRecipes;

    @Singleton
    @Inject
    RecipesRepository recipesRepository;

    @Inject
    RecipeViewModelFactory recipeViewModelFactory;

    RecipesViewModel recipesViewModel;


    private boolean isTablet;


    private AdapterCallback<Recipe> mAdapterCallback = r -> {
        Intent intent = new Intent(getContext(), StepMasterActivity.class);
        int recipeId = r.getId();
        intent.putExtra(StepMasterActivity.KEY_RECIPE_ID, String.valueOf(recipeId));
        Timber.i("selected recipe id: %s", recipeId);
        startActivity(intent);
    };

    public RecipesFragment() {


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Timber.i("isTablet: %s", isTablet);

        initializeInjector();

        adapterRecipes.init(mAdapterCallback);

        isTablet = getResources().getBoolean(R.bool.is_tablet);
        setupRecycler();
        initializeViewModels();
        observe();
    }

    private void initializeInjector() {
        ApplicationComponent component = ((HasComponent<ApplicationComponent>) getActivity().getApplication())
                .getComponent();
        DaggerRecipeComponent.builder()
                             .activityModule(new ActivityModule(this.getActivity()))
                             .applicationComponent(component)
                             .build()
                             .inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    private void initializeViewModels() {
        recipesViewModel = ViewModelProviders.of(this, recipeViewModelFactory)
                                             .get(RecipesViewModel.class);
        recipesViewModel.loadAll();
    }

    private void observe() {
        recipesViewModel.getRecipes()
                        .observeForever(recipes -> {
                            if (recipes == null) {
                                return;
                            }
                            adapterRecipes.swapItemList(recipes);
                            Timber.i("swaping item list: size: %s", recipes.size());
                        });
    }

    private void setupRecycler() {
        RecyclerView.LayoutManager layoutManager;
        Timber.d("is tablet: %s", isTablet);
        if (!isTablet) {
            layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        } else {
            layoutManager = new GridLayoutManager(getContext(), 3);
        }
        recyclerRecipes.setAdapter(adapterRecipes);
        recyclerRecipes.setLayoutManager(layoutManager);

    }


}
