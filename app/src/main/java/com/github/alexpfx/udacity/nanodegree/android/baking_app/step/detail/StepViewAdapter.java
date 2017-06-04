package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.detail;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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


    public static final int NAVIGATION = 0;
    public static final int PLAYER = 1;
    public static final int STEP = 2;
    private Context mContext;
    private View.OnClickListener previousButtonClickListener;
    private View.OnClickListener nextButtonClickListener;


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({PLAYER, STEP, NAVIGATION})
    @interface ViewTypes {
    }

    private Step mStep;

    protected StepViewAdapter(Context context, View.OnClickListener previousButtonClickListener, View.OnClickListener nextButtonClickListener) {
        mContext = context;
        this.previousButtonClickListener = previousButtonClickListener;


        this.nextButtonClickListener = nextButtonClickListener;
    }

    @Override
    public BaseViewHolder<Step> onCreateViewHolder(ViewGroup viewGroup, @ViewTypes int viewType) {
        Log.d(TAG, "onCreateViewHolder: "+viewGroup.getMeasuredHeight());
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
        View view = inflater.inflate(R.layout.item_player, viewGroup, false);
//        view.setLayoutParams(new RecyclerView.LayoutParams(width, 5 * height / 10));
        return new PlayerViewHolder(view, mContext);
    }

    private StepDetailViewHolder inflateStep(LayoutInflater inflater, ViewGroup viewGroup) {
        final View view = inflater.inflate(R.layout.item_detail_step, viewGroup, false);
//        view.setLayoutParams(new RecyclerView.LayoutParams(width, 4 * height / 10));

        return new StepDetailViewHolder(view, mContext);
    }

    private NavigationViewHolder inflateNavigation(LayoutInflater inflater, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.item_navigation, viewGroup, false);
//        view.setLayoutParams(new RecyclerView.LayoutParams(width, 1 * height / 10));
        return new NavigationViewHolder(view, mContext, previousButtonClickListener, nextButtonClickListener);
    }


    @FunctionalInterface
    interface Chooser<I, J, R> {
        R choose(I input, J input2);
    }

    public void setStep(Step step) {
        mStep = step;
        notifyDataSetChanged();
    }


}
