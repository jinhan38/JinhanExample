package com.jinhanexample.service.musicPlayer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MusicPlayerService extends Service implements AudioManager.OnAudioFocusChangeListener {


    private final static int CONTROL_UPDATE = 10;


    private MediaPlayer currentPlayer;
    private final IBinder iBinder = new LocalBinder();
    private Context mContext;
    private Handler mHandler;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {

                switch (msg.what) {
                    case CONTROL_UPDATE:
                        int currentPosition = 0;
                        if (currentPlayer != null) {
                            currentPosition =currentPlayer.getCurrentPosition();
                        }

                }
            }
        };

    }


    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onAudioFocusChange(int i) {

    }

    public class LocalBinder extends Binder {

        public MusicPlayerService getService() {
            return MusicPlayerService.this;
        }

    }


}
