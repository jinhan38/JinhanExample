package com.jinhanexample.video;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.jinhanexample.Common;
import com.jinhanexample.R;
import com.jinhanexample.databinding.ExoPlayerActivityBinding;


public class ExoPlayerActivity extends AppCompatActivity {


    private ExoPlayerActivityBinding b;
    private ExoPlayer exoPlayer;
    private Context mContext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ExoPlayerActivityBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        mContext = this;

        initExoPlayer();
    }


    private void initExoPlayer() {

        exoPlayer = ExoPlayerFactory.newSimpleInstance(mContext);
        b.playerView.setPlayer(exoPlayer);
//        playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);


        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getApplicationContext(),
                Util.getUserAgent(getApplicationContext(), getApplicationContext().getString(R.string.app_name)));

//        Uri video = Uri.parse("android.resource://" + getPackageName() + "/raw/sound_looping");
//        Log.d(TAG, "initExoPlayer: video : " + video);

//        MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
//                .createMediaSource(mediaSource); // 동영상 url

        String sample = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
        MediaSource mediaSource = buildMediaSource(Uri.parse(sample));


        exoPlayer.prepare(mediaSource);
        exoPlayer.setPlayWhenReady(false);
//        exoPlayer.seekTo(seekto);  //프로그레스바 이동
        exoPlayer.addListener(exoPlayerListener);

    }

    private MediaSource buildMediaSource(Uri uri) {

        String userAgent = Util.getUserAgent(this, "jinhan");

        return new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory(userAgent))
                .createMediaSource(uri);
    }

    private Player.EventListener exoPlayerListener = new Player.EventListener() {
        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

            // playWhenReady
            // true : playing
            // false : stop

            // playbackState
            //  1 : When Activity with ExoPlayer disappears
            //  2 : When state of seekBar changes
            //  3 :  ....   exoplayer가 있는 액티비티에 있는 상태

            /**
             * The player does not have any media to play.
             */
            int STATE_IDLE = 1;
            /**
             * The player is not able to immediately play from its current position. This state typically
             * occurs when more data needs to be loaded.
             */
            int STATE_BUFFERING = 2;
            /**
             * The player is able to immediately play from its current position. The player will be playing if
             * {@link #getPlayWhenReady()} is true, and paused otherwise.
             */
            int STATE_READY = 3;
            /**
             * The player has finished playing the media.
             */
            int STATE_ENDED = 4;


            if (playWhenReady) {
                Common.Companion.setToastGravityCenter(mContext, "play");
            } else {
                Common.Companion.setToastGravityCenter(mContext, "stop");
            }


            switch (playbackState) {
                case 1:

                    Common.Companion.setToastGravityCenter(mContext, "STATE_IDLE");
                    break;
                case 2:

                    Common.Companion.setToastGravityCenter(mContext, "STATE_BUFFERING");
                    break;
                case 3:

                    Common.Companion.setToastGravityCenter(mContext, "STATE_READY");
                    break;
                case 4:

                    Common.Companion.setToastGravityCenter(mContext, "STATE_ENDED");
                    break;
            }

        }
    };
}
