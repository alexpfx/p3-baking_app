package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.detail;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.SharedViewModel;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.IdsTO;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.HasComponent;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.PerActivity;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.di.StepComponent;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.steps.StepViewModelFactory;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.steps.StepsViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepViewFragment extends LifecycleFragment implements SharedViewModel.OnSelectListener<Step> {

    private static final String TAG = "StepViewFragment";

    Unbinder unbinder;

    @BindView(R.id.recycler_step_view)
    RecyclerView mRecyclerStepView;

    StepsViewModel mStepsViewModel;

    @PerActivity
    @Inject
    StepViewAdapter mAdapter;

    SharedViewModel<Step> stepSharedViewModel;

    @PerActivity
    @Inject
    StepViewModelFactory stepViewModelFactory;


    private View.OnClickListener btnClickListener = v -> {
        IdsTO idsTO = (IdsTO) v.getTag();
        Timber.i("Navigation butto clicked: %s", idsTO);
        load(idsTO.getId(), idsTO.getRecipeId());
    };


    private int mStep_id;
    private int mRecipe_id;


    public StepViewFragment() {

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mStepsViewModel = ViewModelProviders.of(this, stepViewModelFactory).get(StepsViewModel.class);
        stepSharedViewModel = ViewModelProviders.of(getActivity()).get("step", SharedViewModel.class);

        stepSharedViewModel.getSelected().observe(this, step -> {
            mAdapter.setData(step);
            load(step.getId(), step.getRecipeId());
        });


    }

    private void initializeInjection() {
        ((HasComponent<StepComponent>) getActivity()).getComponent().inject(this);
    }


    private void load(int id, int recipeId) {

        mStepsViewModel.load(id, recipeId);

        mStepsViewModel.getStep().observe(this, step -> {
            if (step == null) {
                Toast.makeText(getContext(), "Step doesn't exist!", Toast.LENGTH_SHORT).show();
                return;
            }
            mAdapter.setData(step);
        });
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initializeInjection();

        View view = inflater.inflate(R.layout.fragment_step_view, container, false);
        unbinder = ButterKnife.bind(this, view);

        setupRecyclerView();

        return view;

    }

    private void setupRecyclerView() {
        mRecyclerStepView.setHasFixedSize(true);

        mAdapter.init(btnClickListener, btnClickListener);
        mRecyclerStepView.setAdapter(mAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerStepView.setLayoutManager(layoutManager);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void init(int step_id, int recipe_id) {

        mStep_id = step_id;
        mRecipe_id = recipe_id;
    }

    @Override
    public void selected(Step item) {
        Log.d(TAG, "selected: "+item);
    }
}
