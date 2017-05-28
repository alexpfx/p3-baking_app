package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.detail;


import android.arch.lifecycle.LifecycleFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepIngredientsFragment extends LifecycleFragment {


    @BindView(R.id.recycler_ingredients)
    RecyclerView mRecyclerIngredients;
    @BindView(R.id.recycler_steps)
    RecyclerView mRecyclerSteps;

    private Unbinder mUnbinder;

    public StepIngredientsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_ingredients, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        setupRecyclerView (mRecyclerIngredients);

        return view;
    }

    private void setupRecyclerView(RecyclerView recyclerIngredients) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
