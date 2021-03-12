package com.jinhanexample.video

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.R

class VideoAudioFocusControlKotlin : AppCompatActivity(), SurfaceHolder.Callback {


    companion object {
        private const val TAG = "VideoAudioFocusControlK"
    }

    lateinit var player: MediaPlayer;
    lateinit var surfaceView: SurfaceView;
    lateinit var holder: SurfaceHolder;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video_audio_focus_control_activity)


        try {
            player = MediaPlayer()
            player = MediaPlayer.create(this, resources.getIdentifier("main1", "raw", packageName))
            surfaceView = findViewById(R.id.surfaceView)
            holder = surfaceView.holder
            holder.addCallback(this)
        } catch (e: Exception) {
            Log.d(TAG, "onCreate: error : $e")
        }

    }

    override fun surfaceCreated(p0: SurfaceHolder) {
        Toast.makeText(this, "surfaceCreated", Toast.LENGTH_SHORT).show()
        player.setDisplay(holder)
        try {
            player.prepare()
        } catch (e: Exception) {
            Log.e(TAG,"ERROR",e);
        }
        player.start()
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
        TODO("Not yet implemented")
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        player.stop()
        TODO("Not yet implemented")
    }
}