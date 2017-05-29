package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe.detail.steps;

import android.view.View;
import android.widget.TextView;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by alexandre on 27/05/2017.
 */

public class StepsViewHolder extends BaseViewHolder<Step> {

//    @BindView(R.id.text_step_short_description)
//    TextView textStepShortDescription;
//

    @BindView(R.id.text_step_description)
    TextView textStepDescription;

    public StepsViewHolder(View view) {
        super(view);

    }

    @Override
    public void bind(Step step) {
//        textStepShortDescription.setText(step.getShortDescription());
        textStepDescription.setText(step.getDescription());


    }
}
