package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.detail;


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
import android.widget.Toast;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.BaseApplication;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.BakingAppDatabase;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.detail.ingredients.IngredientsAdapter;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.detail.ingredients.IngredientsRepositoryImpl;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.detail.ingredients.IngredientsViewModel;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.detail.steps.StepsAdapter;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.detail.steps.StepsRepositoryImpl;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.detail.steps.StepsViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.detail.RecipeDetailActivity.KEY_RECIPE_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepIngredientsFragment extends LifecycleFragment {

    private static final String TAG = "StepIngredientsFragment";

    @BindView(R.id.recycler_ingredients)
    RecyclerView mRecyclerIngredients;
    @BindView(R.id.recycler_steps)
    RecyclerView mRecyclerSteps;

    private OnStepClickListener mOnStepClickListener;
    private IngredientsViewModel mIngredientsViewModel;
    private IngredientsAdapter mIngredientsAdapter;

    private StepsViewModel mStepsViewModel;
    private StepsAdapter mStepsAdapter;

    public interface OnStepClickListener {
        void onStepClick (Step step);
    }

    private Unbinder mUnbinder;


    public StepIngredientsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViewModels();
        mIngredientsViewModel.getIngredientsByRecipe().observe(this, ingredients -> {
            Log.d(TAG, "onActivityCreated: "+ingredients);
            mIngredientsAdapter.swapItemList(ingredients);
        });
        mStepsViewModel.getStepsByRecipe().observe(this, steps -> {
            Log.d(TAG, "onActivityCreated: "+steps);
            mStepsAdapter.swapItemList(steps);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_ingredients, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        mIngredientsAdapter = new IngredientsAdapter(getContext());
        setupRecyclerView(mRecyclerIngredients, mIngredientsAdapter);

        mStepsAdapter = new StepsAdapter(getContext(), onStepClick);
        setupRecyclerView(mRecyclerSteps, mStepsAdapter);

        return view;
    }

    private void setupViewModels() {
        BakingAppDatabase database =
                Room.databaseBuilder(getContext(), BakingAppDatabase.class, BaseApplication.DATABASE_NAME).build();
        mIngredientsViewModel = ViewModelProviders.of(this, new ViewModelProvider.Factory() {
            @Override
            public <T extends ViewModel> T create(Class<T> modelClass) {
                IngredientsViewModel vm = new IngredientsViewModel(new IngredientsRepositoryImpl(database.ingredientDao()));
                String id = getRecipeId();
                vm.initialize(Integer.valueOf(id));
                Toast.makeText(getContext(), "id (id): "+id, Toast.LENGTH_SHORT).show();
                return (T) vm;
            }
        }).get(IngredientsViewModel.class);

        mStepsViewModel = ViewModelProviders.of(this, new ViewModelProvider.Factory() {
            @Override
            public <T extends ViewModel> T create(Class<T> modelClass) {
                StepsViewModel vm = new StepsViewModel(new StepsRepositoryImpl(database.stepDao()));
                String recipeId = getRecipeId();
                vm.initialize(Integer.valueOf(recipeId));
                return (T) vm;
            }
        }).get(StepsViewModel.class);

    }

    private String getRecipeId() {
        return getArguments().getString(KEY_RECIPE_ID);
    }

    private void setupRecyclerView(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

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


    private View.OnClickListener onStepClick = view -> {
        Step step = (Step) view.getTag();
        mOnStepClickListener.onStepClick(step);
    };


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnStepClickListener){
            mOnStepClickListener = (OnStepClickListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnStepClickListener = null;
    }

    public static StepIngredientsFragment newInstance(String recipeId) {
        Bundle args = new Bundle();
        args.putString(KEY_RECIPE_ID, recipeId);
        StepIngredientsFragment stepIngredientsFragment = new StepIngredientsFragment();
        stepIngredientsFragment.setArguments(args);
        return stepIngredientsFragment;
    }

}
