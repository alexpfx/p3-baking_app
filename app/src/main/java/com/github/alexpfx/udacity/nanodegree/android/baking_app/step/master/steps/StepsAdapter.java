package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.steps;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.AdapterCallback;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by alexandre on 28/05/2017.
 */

public class StepsAdapter extends RecyclerView.Adapter <StepsViewHolder> implements View.OnClickListener {
    private Context mContext;
    private AdapterCallback<Step> mAdapterCallback;
    private List<Step> itemList = new ArrayList<>();

    @Inject
    public StepsAdapter(Context context) {
        mContext = context;
    }

    public void init (AdapterCallback<Step> adapterCallback){
        this.mAdapterCallback = adapterCallback;
    }


    @Override
    public StepsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_step, viewGroup, false);
        view.setOnClickListener(this);
        return new StepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepsViewHolder holder, int position) {
        Step step = itemList.get(position);
        holder.bind(step);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public void onClick(View v) {
        mAdapterCallback.receive((Step) v.getTag());
    }

    public void swapItemList(List<Step> steps) {
        itemList = steps;
        notifyDataSetChanged();
    }
}
