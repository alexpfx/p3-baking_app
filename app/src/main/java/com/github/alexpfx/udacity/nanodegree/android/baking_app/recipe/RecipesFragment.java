package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
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
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.BakingAppDatabase;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.StepMasterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecipesFragment extends LifecycleFragment {

    private static final String TAG = "RecipesFragment";
    @BindView(R.id.recycler_recipes)
    RecyclerView recyclerRecipes;

    private RecipesAdapter adapterRecipes;

    private AdapterCallback<Recipe> mAdapterCallback = r -> {
        Intent intent = new Intent(getContext(), StepMasterActivity.class);
        intent.putExtra(StepMasterActivity.KEY_RECIPE_ID, String.valueOf(r.getId()));
        startActivity(intent);
    };

    private RecipesViewModel mViewModel;
    private boolean mIsTablet;

    public RecipesFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mIsTablet = getResources().getBoolean(R.bool.is_tablet);

        initializeViewModels();
        observe();

    }

    private void initializeViewModels() {
        BakingAppDatabase database =
                Room.databaseBuilder(getActivity().getApplicationContext(), BakingAppDatabase.class, "baking_database").build();

        mViewModel = ViewModelProviders.of(this, new ViewModelProvider.Factory() {
            @Override
            public <T extends ViewModel> T create(Class<T> modelClass) {
                RecipesViewModel viewModel = new RecipesViewModel(getActivity().getApplication(),
                        new RecipesRepositoryImpl(new LocalDataSource(database)));
                return (T) viewModel;
            }
        }).get(RecipesViewModel.class);

        mViewModel.loadAll();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);
        ButterKnife.bind(this, view);

        setupRecycler();

        return view;

    }

    private void observe() {
        mViewModel.getRecipes().observeForever(recipes -> {
            adapterRecipes.swapItemList(recipes);
        });
    }

    private void setupRecycler() {
        adapterRecipes = new RecipesAdapter(getContext(), mAdapterCallback);

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
