package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.detail;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
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
import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.SharedViewModel;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;

import java.util.ArrayList;
import java.util.List;

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
    private Step mSelectedStep = null;

    public StepViewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SharedViewModel<Step> sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        sharedViewModel.getSelected().observe(this, step -> {
            mSelectedStep = step;
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
        StepViewAdapter adapter = new StepViewAdapter(getContext());
        adapter.swapItemList(createList());
        mRecyclerStepView.setAdapter(adapter);

        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                layoutManager.getOrientation());

        mRecyclerStepView.addItemDecoration(dividerItemDecoration);
        mRecyclerStepView.setLayoutManager(layoutManager);


    }

    private List<Step> createList() {
        ArrayList <Step> a = new ArrayList<>();
        a.add(new Step());
        a.add(new Step());
        a.add(new Step());
        return a;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
