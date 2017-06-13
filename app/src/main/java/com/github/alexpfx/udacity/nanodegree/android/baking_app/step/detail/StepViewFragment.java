package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.detail;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.BaseApplication;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.SharedViewModel;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.BakingAppDatabase;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.IdsTO;
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

    Unbinder unbinder;

    @BindView(R.id.recycler_step_view)


    RecyclerView mRecyclerStepView;

    private StepsViewModel mStepsViewModel;
    private StepViewAdapter mAdapter;
    private SharedViewModel<Step> mSharedViewModel;


    private View.OnClickListener btnClickListener = v -> {
        IdsTO idsTO = (IdsTO) v.getTag();
        load(idsTO.getId(), idsTO.getRecipeId());
    };

    private StepsRepositoryImpl mRepository;

    private int mStep_id;
    private int mRecipe_id;


    private void load(int id, int recipeId) {
        mStepsViewModel.load(id, recipeId);

        mStepsViewModel.getStep().observe(this, step -> {
            if (step == null) {
                Toast.makeText(getContext(), "The step doesn't exist!", Toast.LENGTH_SHORT).show();
                return;
            }
            mSharedViewModel.select(step);
            mAdapter.setData(step);
        });
    }

    public StepViewFragment() {

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        BakingAppDatabase database =
                Room.databaseBuilder(getContext(), BakingAppDatabase.class, BaseApplication.DATABASE_NAME).build();
        mRepository = new StepsRepositoryImpl(database.stepDao());


        mStepsViewModel = ViewModelProviders.of(getActivity(), new ViewModelProvider.Factory() {
            @Override
            public <T extends ViewModel> T create(Class<T> modelClass) {
                return (T) new StepsViewModel(mRepository);
            }
        }).get(StepsViewModel.class);

        mSharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        mSharedViewModel.getSelected().observe(this, step -> {
            mAdapter.setData(step);
        });

        load(mStep_id, mRecipe_id);
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
        mRecyclerStepView.setHasFixedSize(true);

        mAdapter = new StepViewAdapter(getContext(), btnClickListener, btnClickListener);

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
}
