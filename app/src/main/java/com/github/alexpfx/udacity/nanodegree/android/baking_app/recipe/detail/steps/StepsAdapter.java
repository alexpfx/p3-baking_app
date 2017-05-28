package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.detail.steps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.BaseAdapter;

/**
 * Created by alexandre on 28/05/2017.
 */

public class StepsAdapter extends BaseAdapter<Step, StepsViewHolder> {
    private View.OnClickListener mOnClickListener;

    protected StepsAdapter(Context context, View.OnClickListener onClickListener) {
        super(context);
        mOnClickListener = onClickListener;
    }

    @Override
    public StepsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_step, viewGroup, false);
        return new StepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepsViewHolder holder, int position) {
        Step step = getItemAt(position);
        holder.setOnClickListener(mOnClickListener, step);
    }
}
