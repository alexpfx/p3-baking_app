package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.ingredients;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.AdapterCallback;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Ingredient;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by alexandre on 27/05/2017.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsViewHolder> {

    private AdapterCallback<Ingredient> mAdapterCallback;

    private List<Ingredient> itemList = new ArrayList<>();
    private Context mContext;

    @Inject
    public IngredientsAdapter(Context context) {
        mContext = context;
    }

    @Override
    public IngredientsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_ingredient, viewGroup, false);
        return new IngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientsViewHolder holder, int position) {
        Ingredient ingredient = itemList.get(position);
        holder.bind(ingredient);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    private static final String TAG = "IngredientsAdapter";


    public void swapItemList(List<Ingredient> ingredients) {
        itemList = ingredients;
        Log.d(TAG, "swapItemList: ");
        notifyDataSetChanged();
    }
}
