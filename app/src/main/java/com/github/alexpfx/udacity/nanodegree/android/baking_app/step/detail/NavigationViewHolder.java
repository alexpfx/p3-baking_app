package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.detail;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.BaseViewHolder;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexandre on 30/05/2017.
 */

public class NavigationViewHolder extends BaseViewHolder<Step> {
    private static final String TAG = "NavigationViewHolder";

    @BindView(R.id.btn_previous)
    ImageButton btnPrevious;

    @BindView(R.id.btn_next)
    ImageButton btnNext;

    @BindView(R.id.text_short_description)
    TextView textShortDescription;


    public NavigationViewHolder(View view, Context context, View.OnClickListener previousButtonClickListener, View.OnClickListener nextButtonClickListener) {
        super(view, context);
        ButterKnife.bind(this, view);

        btnNext.setOnClickListener(nextButtonClickListener);
        btnPrevious.setOnClickListener(previousButtonClickListener);

    }

    @Override
    public void bind(Step step) {
        textShortDescription.setText(step.getShortDescription());
    }


}
