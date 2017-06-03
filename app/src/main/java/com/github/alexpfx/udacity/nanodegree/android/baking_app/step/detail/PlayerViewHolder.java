package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.detail;

import android.content.Context;
import android.net.Uri;
import android.view.View;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.base.BaseViewHolder;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexandre on 30/05/2017.
 */

public class PlayerViewHolder extends BaseViewHolder<Step> {

    @BindView(R.id.video_player_view)
    SimpleExoPlayerView mSimpleExoPlayerView;

    private ExoPlayer mExoPlayer;

    public PlayerViewHolder(View view, Context context) {
        super(view, context);
        ButterKnife.bind(this, view);
        mExoPlayer = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(context), new DefaultTrackSelector(), new DefaultLoadControl());
        mSimpleExoPlayerView.setPlayer((SimpleExoPlayer) mExoPlayer);


    }

    private static final String TAG = "PlayerViewHolder";
    @Override
    public void bind(Step step) {
        if (step.getVideoURL() == null){
            return;
        }
        String videoURL = step.getVideoURL();
        MediaSource mediaSource = new ExtractorMediaSource(
                Uri.parse(videoURL),
                new DefaultDataSourceFactory(
                        getContext(),
                        Util.getUserAgent(getContext(), "recipePlayer")),
                new DefaultExtractorsFactory(), null, null);

        mExoPlayer.prepare(mediaSource);
        mExoPlayer.setPlayWhenReady(true);


    }
}
