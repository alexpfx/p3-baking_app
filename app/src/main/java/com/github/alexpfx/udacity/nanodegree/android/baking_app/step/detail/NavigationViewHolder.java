package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.IdsTO;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by alexandre on 30/05/2017.
 */

public class NavigationViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "NavigationViewHolder";

    @BindView(R.id.btn_previous)
    ImageButton btnPrevious;

    @BindView(R.id.btn_next)
    ImageButton btnNext;

    @BindView(R.id.text_short_description)
    TextView textShortDescription;


    public NavigationViewHolder(View view, Context context, View.OnClickListener previousButtonClickListener, View
            .OnClickListener nextButtonClickListener) {
        super(view);
        ButterKnife.bind(this, view);

        btnNext.setOnClickListener(nextButtonClickListener);
        btnPrevious.setOnClickListener(previousButtonClickListener);

    }

    public void bind(Step step) {
        if (step == null) {
            return;
        }
        Timber.d("step: ", step);
        int stepId = step.getId();
        int recipeId = step.getRecipeId();

        btnNext.setTag(new IdsTO(stepId + 1, recipeId));
        btnPrevious.setTag(new IdsTO(stepId - 1, recipeId));

        textShortDescription.setText(step.getShortDescription());
    }


}
