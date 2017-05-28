package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.list;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.BakingAppDatabase;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipesFragment extends LifecycleFragment {

    private static final String TAG = "RecipesFragment";
    @BindView(R.id.recycler_recipes)
    RecyclerView recyclerRecipes;

    private RecipesAdapter adapterRecipes;
    private View.OnClickListener onItemClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Recipe r = (Recipe) v.getTag();
        }
    };
    private RecipesViewModel mViewModel;

    public RecipesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Log.d(TAG, "onCreateView: ");

        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

        ButterKnife.bind(this, view);

        mViewModel = ViewModelProviders.of(this).get(RecipesViewModel.class);
        BakingAppDatabase database =
                Room.databaseBuilder(getContext(), BakingAppDatabase.class, "baking_database").build();

        mViewModel.injectRepository(new RecipesRepositoryImpl(database));
        setupRecycler ();



        mViewModel.getRecipes().observe(this, recipes -> {
            adapterRecipes.swapItemList(recipes);
            Log.d(TAG, "onCreateView: "+recipes);
        });


        return view;

    }

    private void setupRecycler() {

        adapterRecipes = new RecipesAdapter(getContext(), onItemClick);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerRecipes.setLayoutManager(layoutManager);
        recyclerRecipes.setAdapter(adapterRecipes);

        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerRecipes.getContext(),
                layoutManager.getOrientation());

        recyclerRecipes.addItemDecoration(dividerItemDecoration);


    }

}
