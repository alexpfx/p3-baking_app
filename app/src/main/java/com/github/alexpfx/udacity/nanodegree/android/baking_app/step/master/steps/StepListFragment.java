package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.steps;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
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
import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.BaseApplication;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.SharedViewModel;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.BakingAppDatabase;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.StepMasterActivity;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.ingredients.IngredientsAdapter;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.ingredients.IngredientsRepositoryImpl;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.ingredients.IngredientsViewModel;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepListFragment extends LifecycleFragment {

    private static final String TAG = "StepListFragment";

    @BindView(R.id.recycler_ingredients)
    RecyclerView mRecyclerIngredients;
    @BindView(R.id.recycler_steps)
    RecyclerView mRecyclerSteps;

    private IngredientsViewModel mIngredientsViewModel;
    private IngredientsAdapter mIngredientsAdapter;

    private StepsViewModel mStepsViewModel;


    private SharedViewModel<Step> mStepSelectorViewModel;

    private StepsAdapter mStepsAdapter;


    private Unbinder mUnbinder;
    private String mRecipeId;


    public StepListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupViewModels();

        observe();

    }

    private void observe() {
        mIngredientsViewModel.getIngredientsByRecipe().observe(this, ingredients -> {
            Log.d(TAG, "onActivityCreated: " + ingredients);
            mIngredientsAdapter.swapItemList(ingredients);
        });

        mStepsViewModel.getStepsByRecipe().observe(this, steps -> {
            Log.d(TAG, "onActivityCreated: " + steps);
            mStepsAdapter.swapItemList(steps);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_ingredients, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        mStepsAdapter = new StepsAdapter(getContext(), onStepClick);
        mIngredientsAdapter = new IngredientsAdapter(getContext());

        setupRecyclerView(mRecyclerIngredients, mIngredientsAdapter);
        setupRecyclerView(mRecyclerSteps, mStepsAdapter);



        return view;
    }

    private void setupViewModels() {
        mStepSelectorViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        BakingAppDatabase database =
                Room.databaseBuilder(getContext(), BakingAppDatabase.class, BaseApplication.DATABASE_NAME).build();

        mIngredientsViewModel = ViewModelProviders.of(this, new ViewModelProvider.Factory() {
            @Override
            public <T extends ViewModel> T create(Class<T> modelClass) {
                IngredientsViewModel ingredientsViewModel = new IngredientsViewModel(new IngredientsRepositoryImpl(database.ingredientDao()));
                String id = mRecipeId;
                ingredientsViewModel.loadAllByRecipeId(Integer.valueOf(id));
                return (T) ingredientsViewModel;
            }
        }).get(IngredientsViewModel.class);

        mStepsViewModel = ViewModelProviders.of(this, new ViewModelProvider.Factory() {
            @Override
            public <T extends ViewModel> T create(Class<T> modelClass) {
                StepsViewModel stepsViewModel = new StepsViewModel(new StepsRepositoryImpl(database.stepDao()));
                String recipeId = mRecipeId;
                stepsViewModel.loadAllByRecipeId(Integer.valueOf(recipeId));
                return (T) stepsViewModel;
            }
        }).get(StepsViewModel.class);

    }


    public void init(String recipeId){
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
        mUnbinder.unbind();
    }


    private AdapterCallback<Step> onStepClick = step -> {
        mStepSelectorViewModel.select(step);

        EventBus.getDefault().post(step);
    };


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    public static StepListFragment newInstance(String recipeId) {
        Bundle args = new Bundle();
        args.putString(StepMasterActivity.KEY_RECIPE_ID, recipeId);
        StepListFragment stepListFragment = new StepListFragment();
        stepListFragment.setArguments(args);
        return stepListFragment;
    }

}
