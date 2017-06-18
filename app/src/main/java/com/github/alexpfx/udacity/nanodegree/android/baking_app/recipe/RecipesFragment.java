package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProvider;
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
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.HasComponent;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.di.RecipesComponent;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.StepMasterActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecipesFragment extends LifecycleFragment {

    private static final String TAG = "RecipesFragment";

    @BindView(R.id.recycler_recipes)
    RecyclerView recyclerRecipes;

    @Inject
    RecipesAdapter adapterRecipes;

    @Inject
    RecipesRepository recipesRepository;

    @Inject
    ViewModelProvider.Factory factory;

    private RecipesViewModel mViewModel;

    private boolean mIsTablet;

    private AdapterCallback<Recipe> mAdapterCallback = r -> {
        Intent intent = new Intent(getContext(), StepMasterActivity.class);
        intent.putExtra(StepMasterActivity.KEY_RECIPE_ID, String.valueOf(r.getId()));
        startActivity(intent);
    };

    public RecipesFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((HasComponent<RecipesComponent>) this.getActivity()).getComponent().inject(this);
        adapterRecipes.init(mAdapterCallback);
        setupRecycler();
        mIsTablet = getResources().getBoolean(R.bool.is_tablet);
        initializeViewModels();
        observe();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    private void initializeViewModels() {
        mViewModel = ViewModelProviders.of(this, factory).get(RecipesViewModel.class);
        mViewModel.loadAll();

    }

    private void observe() {
        mViewModel.getRecipes().observeForever(recipes -> {
            adapterRecipes.swapItemList(recipes);
        });
    }

    private void setupRecycler() {
        RecyclerView.LayoutManager layoutManager;

        if (!mIsTablet) {
            layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        } else {
            layoutManager = new GridLayoutManager(getContext(), 3);
        }
        recyclerRecipes.setAdapter(adapterRecipes);
        recyclerRecipes.setLayoutManager(layoutManager);

    }

}
