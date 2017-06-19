package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.steps;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.AdapterCallback;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.SharedViewModel;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.ApplicationModule;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.ContextModule;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.di.DaggerStepMasterComponent;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.di.ViewModelModule;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.ingredients.IngredientsAdapter;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.ingredients.IngredientsViewModel;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepListFragment extends LifecycleFragment {

    private static final String TAG = "StepListFragment";

    @BindView(R.id.recycler_ingredients)
    RecyclerView mRecyclerIngredients;
    @BindView(R.id.recycler_steps)
    RecyclerView mRecyclerSteps;

    @Inject
    IngredientsAdapter ingredientsAdapter;

    @Inject
    StepsAdapter mStepsAdapter;

    @Inject
    IngredientsViewModel ingredientsViewModel;

    @Inject
    StepsViewModel stepsViewModel;

    private SharedViewModel<Step> mStepSelectorViewModel;

    private String mRecipeId;


    public StepListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        DaggerStepMasterComponent.builder().applicationModule(new ApplicationModule(getActivity().getApplication())).contextModule(new ContextModule(getActivity()))
                .viewModelModule(new ViewModelModule(this)).build().inject(this);

        setupViewModels();


        mStepsAdapter.init(onStepClick);

        setupRecyclerView(mRecyclerIngredients, ingredientsAdapter);
        setupRecyclerView(mRecyclerSteps, mStepsAdapter);

        observe();

    }

    private void observe() {
        ingredientsViewModel.getIngredientsByRecipe().observe(this, ingredients -> {
            Log.d(TAG, "onActivityCreated: " + ingredients);
            ingredientsAdapter.swapItemList(ingredients);
        });

        stepsViewModel.getStepsByRecipe().observe(this, steps -> {
            Log.d(TAG, "onActivityCreated: " + steps);
            mStepsAdapter.swapItemList(steps);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_ingredients, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    private void setupViewModels() {
        mStepSelectorViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        ingredientsViewModel.loadAllByRecipeId(Integer.valueOf(mRecipeId));

        stepsViewModel.loadAllByRecipeId(Integer.valueOf(mRecipeId));

    }


    public void init(String recipeId) {
        mRecipeId = recipeId;

    }

    private void setupRecyclerView(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                layoutManager.getOrientation());

        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setNestedScrollingEnabled(false);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    private AdapterCallback<Step> onStepClick = step -> {
        mStepSelectorViewModel.select(step);

        EventBus.getDefault().post(step);
    };


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
