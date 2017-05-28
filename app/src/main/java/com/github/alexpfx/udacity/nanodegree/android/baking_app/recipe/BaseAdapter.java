package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe;

import android.support.v7.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

/**
 * Created by alexandre on 27/05/2017.
 */

public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<T> mItemList = Collections.emptyList();

    public List<T> getItemList() {
        return mItemList;
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }


    public void swapItemList(List<T> items) {
        mItemList = items;
        notifyDataSetChanged();
    }

}
