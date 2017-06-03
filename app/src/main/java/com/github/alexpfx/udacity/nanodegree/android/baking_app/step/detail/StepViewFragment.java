package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.detail;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
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
import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.BaseApplication;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.SharedViewModel;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.BakingAppDatabase;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.steps.StepsRepositoryImpl;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.steps.StepsViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepViewFragment extends LifecycleFragment {


    private static final String TAG = "StepViewFragment";

    @BindView(R.id.recycler_step_view)
    RecyclerView mRecyclerStepView;

    Unbinder unbinder;
    private StepsViewModel mStepsViewModel;
    private StepViewAdapter mAdapter;

    public StepViewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BakingAppDatabase database =
                Room.databaseBuilder(getContext(), BakingAppDatabase.class, BaseApplication.DATABASE_NAME).build();

        mStepsViewModel = ViewModelProviders.of(getActivity(), new ViewModelProvider.Factory() {
            @Override
            public <T extends ViewModel> T create(Class<T> modelClass) {
                return (T) new StepsViewModel(new StepsRepositoryImpl(database.stepDao()));
            }
        }).get(StepsViewModel.class);

        SharedViewModel<Step> sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        sharedViewModel.getSelected().observe(this, step -> {
            mStepsViewModel.load(step.getId());
            mAdapter.setStep(step);
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_step_view, container, false);
        unbinder = ButterKnife.bind(this, view);

        setupRecyclerView();

        return view;

    }

    private void setupRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new StepViewAdapter(getContext());

        mRecyclerStepView.setAdapter(mAdapter);

        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                layoutManager.getOrientation());

        mRecyclerStepView.addItemDecoration(dividerItemDecoration);
        mRecyclerStepView.setLayoutManager(layoutManager);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
