package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.detail;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Inject;

/**
 * Created by alexandre on 30/05/2017.
 */


public class StepViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements LifecycleObserver {


    public static final int NAVIGATION = 0;
    public static final int PLAYER = 1;
    public static final int STEP = 2;
    private Context mContext;
    private View.OnClickListener previousButtonClickListener;
    private View.OnClickListener nextButtonClickListener;


    public void init(View.OnClickListener previousButtonClickListener, View.OnClickListener nextButtonClickListener) {
        this.previousButtonClickListener = previousButtonClickListener;
        this.nextButtonClickListener = nextButtonClickListener;
    }


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({PLAYER, STEP, NAVIGATION})
    @interface ViewTypes {
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {

    }

    private Step mStep;

    @Inject
    public StepViewAdapter(Context context) {
        mContext = context;

    }

    private static final String TAG = "StepViewAdapter";

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (mStep == null) {
            return 0;
        }
        return 3;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, @ViewTypes int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        switch (viewType) {
            case PLAYER:
                return inflatePlayer(inflater, viewGroup);
            case STEP:
                return inflateStep(inflater, viewGroup);
            case NAVIGATION:
                return inflateNavigation(inflater, viewGroup);
            default:
                throw new RuntimeException("not a valid view!");
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case PLAYER:
                ((PlayerViewHolder) holder).bind(mStep);

                break;
            case STEP:
                ((StepDetailViewHolder) holder).bind(mStep);
                break;
            case NAVIGATION:
                ((NavigationViewHolder) holder).bind(mStep);
                break;
        }
    }


    private PlayerViewHolder inflatePlayer(LayoutInflater inflater, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.item_player, viewGroup, false);
        return new PlayerViewHolder(view, mContext);
    }

    private StepDetailViewHolder inflateStep(LayoutInflater inflater, ViewGroup viewGroup) {
        final View view = inflater.inflate(R.layout.item_detail_step, viewGroup, false);
        return new StepDetailViewHolder(view);
    }

    private NavigationViewHolder inflateNavigation(LayoutInflater inflater, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.item_navigation, viewGroup, false);
        return new NavigationViewHolder(view, mContext, previousButtonClickListener, nextButtonClickListener);
    }

    public void setData(Step step) {
        mStep = step;
        notifyDataSetChanged();
    }


}
