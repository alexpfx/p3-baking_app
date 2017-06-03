package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.detail;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.BaseViewHolder;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexandre on 30/05/2017.
 */

public class StepDetailViewHolder extends BaseViewHolder<Step> {

    @BindView(R.id.text_step_description)
    TextView mTextStepDescription;

    public StepDetailViewHolder(View view, Context context) {
        super(view, context);
        ButterKnife.bind(this, view);
    }

    @Override
    public void bind(Step step) {
        mTextStepDescription.setText(step.getDescription());
    }
}
