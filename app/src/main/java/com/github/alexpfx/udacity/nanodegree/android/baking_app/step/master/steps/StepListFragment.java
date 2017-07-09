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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.AdapterCallback;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.SharedViewModel;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.HasComponent;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.StepComponent;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.ingredients.IngredientsAdapter;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.ingredients.IngredientsViewModel;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.ingredients.IngredientsViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

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

    IngredientsViewModel ingredientsViewModel;

    StepsViewModel stepsViewModel;

    SharedViewModel<Step> stepSharedViewModel;

    private String mRecipeId;
    private SharedViewModel<String> recipeIdSharedViewModel;

    @Inject
    IngredientsViewModelFactory ingredientsViewModelFactory;

    @Inject
    StepViewModelFactory stepViewModelFactory;

    public StepListFragment() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((HasComponent<StepComponent>) getActivity()).getComponent().inject(this);

        ingredientsViewModel = ViewModelProviders.of(this, ingredientsViewModelFactory).get(IngredientsViewModel.class);
        stepsViewModel = ViewModelProviders.of(this, stepViewModelFactory).get(StepsViewModel.class);

        setupViewModels();

        mStepsAdapter.init(onStepClick);

        setupRecyclerView(mRecyclerIngredients, ingredientsAdapter);
        setupRecyclerView(mRecyclerSteps, mStepsAdapter);

    }

    private void observe() {
        ingredientsViewModel.loadAllByRecipeId(Integer.valueOf(mRecipeId));
        stepsViewModel.loadAllByRecipeId(Integer.valueOf(mRecipeId));

        ingredientsViewModel.getIngredientsByRecipe()
                            .observe(this, ingredients -> {
                                ingredientsAdapter.swapItemList(ingredients);
                            });


        stepsViewModel.getStepsByRecipe()
                      .observe(this, steps -> {
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
        recipeIdSharedViewModel = ViewModelProviders.of(getActivity())
                                                    .get("recipe", SharedViewModel.class);

        recipeIdSharedViewModel.getSelected()
                               .observe(this, id -> {
                                   mRecipeId = id;
                                   observe();
                               });

        stepSharedViewModel = ViewModelProviders.of(getActivity())
                                                .get("step", SharedViewModel.class);

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


    private AdapterCallback<Step> onStepClick = step -> {
        Timber.i("step selected: %s", step.getId());
        stepSharedViewModel.select(step);
    };


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
