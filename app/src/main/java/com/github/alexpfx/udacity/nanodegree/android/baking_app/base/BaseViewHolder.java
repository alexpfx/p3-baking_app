package com.github.alexpfx.udacity.nanodegree.android.baking_app.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by alexandre on 28/05/2017.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    private Context mContext;

    public BaseViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public BaseViewHolder(View view, Context context){
        this(view);
        this.mContext = context;

    }

    protected Context getContext() {
        return mContext;
    }



}
