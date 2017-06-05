package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.master.steps;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by alexandre on 27/05/2017.
 */

public class StepsViewHolder extends BaseViewHolder {

    @BindView(R.id.text_step_short_description)
    TextView textStepShortDescription;

    @BindView(R.id.text_step_description)
    TextView textStepDescription;

    @BindView(R.id.image_has_video)
    ImageView mImageAsVideo;

    private View.OnClickListener mOnClickListener;

    public StepsViewHolder(View view, View.OnClickListener onClickListener) {
        super(view);
        mOnClickListener = onClickListener;
    }

    private static final String TAG = "StepsViewHolder";

    public void bind(Step step) {
        setTag(step);
        
        String videoURL = step.getVideoURL();
        Log.d(TAG, "bind: getVideoURL"+ videoURL);
        if (videoURL != null && !videoURL.isEmpty()){
            mImageAsVideo.setVisibility(View.VISIBLE);
        }else{
            mImageAsVideo.setVisibility(View.INVISIBLE);
        }

        textStepDescription.setText(step.getDescription());
        textStepShortDescription.setText(step.getShortDescription());
    }
}
