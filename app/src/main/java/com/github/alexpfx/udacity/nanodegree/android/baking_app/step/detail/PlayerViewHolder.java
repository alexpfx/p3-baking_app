package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.detail;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
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

public class PlayerViewHolder extends RecyclerView.ViewHolder implements LifecycleObserver {

    private final Context mContext;
    @BindView(R.id.video_player_view)
    SimpleExoPlayerView mSimpleExoPlayerView;

    private ExoPlayer mExoPlayer;

    public PlayerViewHolder(View view, Context context) {
        super(view);
        mContext = context;
        ButterKnife.bind(this, view);
        mExoPlayer = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(context), new
                DefaultTrackSelector(), new DefaultLoadControl());
        mSimpleExoPlayerView.setPlayer((SimpleExoPlayer) mExoPlayer);


    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void releasePlayer() {
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }

    private static final String TAG = "PlayerViewHolder";


    public void bind(Step step) {
        Log.d(TAG, "bind: " + step.getVideoURL());
        if (step.getVideoURL() == null || step.getVideoURL()
                                              .isEmpty()) {
            mSimpleExoPlayerView.setVisibility(View.GONE);
            return;
        }
        mSimpleExoPlayerView.setVisibility(View.VISIBLE);

        String videoURL = step.getVideoURL();
        MediaSource mediaSource = new ExtractorMediaSource(
                Uri.parse(videoURL),
                new DefaultDataSourceFactory(
                        mContext,
                        Util.getUserAgent(mContext, "recipePlayer")),
                new DefaultExtractorsFactory(), null, null);

        mExoPlayer.prepare(mediaSource);
        mExoPlayer.setPlayWhenReady(true);

    }


}
