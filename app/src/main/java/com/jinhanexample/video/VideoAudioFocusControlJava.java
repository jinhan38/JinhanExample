package com.jinhanexample.video;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jinhanexample.R;

public class VideoAudioFocusControlJava extends AppCompatActivity implements SurfaceHolder.Callback {

    private static final String TAG = "VideoAudioFocusControl";



    MediaPlayer player;
    SurfaceView surfaceView;
    SurfaceHolder holder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_audio_focus_control_activity);


        try {

            player = new MediaPlayer();
            player = MediaPlayer.create(this, getResources().getIdentifier("main1", "raw", getPackageName()));
            surfaceView = findViewById(R.id.surfaceView);
            holder = surfaceView.getHolder();
            holder.addCallback(this);

        } catch (Exception e) {
            Log.d(TAG, "onCreate: error : " + e);
        }

    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        Toast.makeText(getApplicationContext(), "surfaceCreated", Toast.LENGTH_LONG).show();
        player.setDisplay(holder);
        try {
            player.prepare();
        } catch (Exception e) {
//            Log.e(TAG,"ERROR",e);
        }
        player.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        Toast.makeText(getApplicationContext(), "surfaceDestroyed", Toast.LENGTH_LONG).show();
    }



}
