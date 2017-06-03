package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.detail;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.BaseViewHolder;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by alexandre on 30/05/2017.
 */


public class StepViewAdapter extends RecyclerView.Adapter<BaseViewHolder<Step>> {


    public static final int PLAYER = 0;
    public static final int STEP = 1;
    public static final int NAVIGATION = 2;
    private Context mContext;


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({PLAYER, STEP, NAVIGATION})
    @interface ViewTypes {
    }

    private Step mStep;

    protected StepViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public BaseViewHolder<Step> onCreateViewHolder(ViewGroup viewGroup, @ViewTypes int viewType) {
        return chooseViewHolder.choose(viewType, viewGroup);
    }

    private static final String TAG = "StepViewAdapter";

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<Step> holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
        holder.bind(mStep);

    }


    Chooser<Integer, ViewGroup, BaseViewHolder<Step>> chooseViewHolder = (viewType, viewGroup) -> {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        Log.d(TAG, "viewtype: " + viewType);
        switch (viewType) {
            case PLAYER:
                return inflatePlayer(layoutInflater, viewGroup);
            case STEP:
                return inflateStep(layoutInflater, viewGroup);
            case NAVIGATION:
                return inflateNavigation(layoutInflater, viewGroup);
            default:
                throw new RuntimeException("not a valid view!");
        }
    };


    private PlayerViewHolder inflatePlayer(LayoutInflater inflater, ViewGroup viewGroup) {
        return new PlayerViewHolder(inflater.inflate(R.layout.item_player, viewGroup, false), mContext);
    }

    private StepDetailViewHolder inflateStep(LayoutInflater inflater, ViewGroup viewGroup) {
        return new StepDetailViewHolder(inflater.inflate(R.layout.item_detail_step, viewGroup, false), mContext);
    }

    private NavigationViewHolder inflateNavigation(LayoutInflater inflater, ViewGroup viewGroup) {
        return new NavigationViewHolder(inflater.inflate(R.layout.item_navigation, viewGroup, false), mContext);
    }


    @FunctionalInterface
    interface Chooser<I, J, R> {
        R choose(I input, J input2);
    }

    public void setStep(Step step) {
        mStep = step;
    }


}
