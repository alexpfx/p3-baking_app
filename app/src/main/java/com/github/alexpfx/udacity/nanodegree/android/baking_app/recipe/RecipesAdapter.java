package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.AdapterCallback;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by alexandre on 25/05/2017.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesViewHolder> implements View.OnClickListener {

    private static final String TAG = "RecipesAdapter";
    private final Context mContext;

    private AdapterCallback<Recipe> mAdapterCallback;


    private List<Recipe> mItemList = new ArrayList<>();

    @Inject
    public RecipesAdapter(Context context) {
        mContext = context;
    }

    public void init (AdapterCallback callback){
        this.mAdapterCallback = callback;
    }


    @Override
    public RecipesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recipe, viewGroup, false);
        view.setOnClickListener(this);
        return new RecipesViewHolder(view, mContext);
    }

    @Override
    public void onBindViewHolder(RecipesViewHolder holder, int position) {
        Recipe recipe = mItemList.get(position);
        holder.bind(recipe);
    }
    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: ");
        return mItemList.size();
    }


    @Override
    public void onClick(View v) {
        mAdapterCallback.receive((Recipe) v.getTag());
    }

    public void swapItemList(List<Recipe> recipes) {
        if (recipes == null){
            return;
        }

        this.mItemList = recipes;
        notifyDataSetChanged();
    }
}
