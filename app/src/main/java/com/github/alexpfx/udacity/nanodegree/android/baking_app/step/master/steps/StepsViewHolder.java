package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.steps;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexandre on 27/05/2017.
 */

public class StepsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_step_short_description)
    TextView textStepShortDescription;

    @BindView(R.id.text_step_description)
    TextView textStepDescription;

    @BindView(R.id.image_has_video)
    ImageView mImageAsVideo;


    public StepsViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    private static final String TAG = "StepsViewHolder";

    public void bind(Step step) {
        itemView.setTag(step);
        String videoURL = step.getVideoURL();
        Log.d(TAG, "bind: getVideoURL" + videoURL);
        if (videoURL != null && !videoURL.isEmpty()) {
            mImageAsVideo.setVisibility(View.VISIBLE);
        } else {
            mImageAsVideo.setVisibility(View.INVISIBLE);
        }

        textStepDescription.setText(step.getDescription());
        textStepShortDescription.setText(step.getShortDescription());

    }
}
