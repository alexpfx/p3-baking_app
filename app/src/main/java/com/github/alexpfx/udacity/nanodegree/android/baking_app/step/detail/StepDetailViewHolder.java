package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexandre on 30/05/2017.
 */

public class StepDetailViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_step_description)
    TextView mTextStepDescription;

    public StepDetailViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bind(Step step) {
        mTextStepDescription.setText(step.getDescription());
    }
}
