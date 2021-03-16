package com.jinhanexample.service.musicPlayer;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.MediaController;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.jinhanexample.R;
import com.jinhanexample.databinding.LayoutFloatingWidgetBinding;

import java.io.IOException;

public class MusicPlayerService extends Service implements AudioManager.OnAudioFocusChangeListener {

    private static final String TAG = "MusicPlayerService";

    private final static int CONTROL_UPDATE = 10;


    private MediaPlayer currentPlayer;
    private final IBinder iBinder = new LocalBinder();
    private Context mContext;
    private Handler mHandler;
    //    private View miniPlayerView;
    private LayoutFloatingWidgetBinding playerView;


    private TelephonyManager telephonyManager;
    private PhoneStateListener phoneStateListener;
    private boolean ongoingCall = false;

    //audioFocus
    private AudioManager audioManager;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Log.d(TAG, "onCreate: 진입");

        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {

                switch (msg.what) {
                    case CONTROL_UPDATE:
                        int currentPosition = 0;
                        if (currentPlayer != null) {
                            currentPosition = currentPlayer.getCurrentPosition();
                        }

                }
            }
        };
        playerView = LayoutFloatingWidgetBinding.inflate(LayoutInflater.from(this));
//        miniPlayerView = LayoutInflater.from(mContext).inflate(R.layout.layout_floating_widget, null);

        initMediaPlayer();

        callStateListener();

        registerRemoveEarphone();

        register_AudioControl();

    }


    private void initMediaPlayer() {
        requestAudioFocus();

        if (currentPlayer == null) {
            currentPlayer = new MediaPlayer();
        }

        currentPlayer.setOnCompletionListener(onCompletionListener);
        currentPlayer.setOnErrorListener(onErrorListener);
        currentPlayer.setOnPreparedListener(onPreparedListener);
        currentPlayer.setOnBufferingUpdateListener(onBufferingUpdateListener);
        currentPlayer.setOnSeekCompleteListener(onSeekCompleteListener);
        currentPlayer.setOnInfoListener(onInfoListener);
        currentPlayer.reset();
        String strUrl = "android.resource://" + getPackageName() + "/raw/bensound";
//        Uri sound = Uri.parse("android.resource://" + getPackageName() + "/raw/bensound");
//        MediaSource mediaSource = buildMediaSource(sound);
        try {
            currentPlayer.setDataSource(this, Uri.parse(strUrl));
        } catch (IOException e) {
            Log.d(TAG, "initMediaPlayer: e : " + e);
            e.printStackTrace();
        }

        currentPlayer.prepareAsync();

    }

    private MediaSource buildMediaSource(Uri uri) {

        String userAgent = Util.getUserAgent(this, "jinhan");

        return new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory(userAgent))
                .createMediaSource(uri);
    }

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            if (playerView != null) {
                playerView.ivPlayButton.setActivated(true);
            }
            Log.d(TAG, "onCompletion: ");
        }
    };

    private MediaPlayer.OnErrorListener onErrorListener = (mediaPlayer, what, i1) -> {
        Log.d(TAG, "onErrorListener onError: " + what);
        return false;
    };

    private MediaPlayer.OnPreparedListener onPreparedListener = (mediaPlayer -> {
        Log.d(TAG, "OnPreparedListener : ");
    });

    private MediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener = ((mediaPlayer, percent) -> {
        Log.d(TAG, "onBufferingUpdateListener : percent : " + percent);
    });

    private MediaPlayer.OnSeekCompleteListener onSeekCompleteListener = (mediaPlayer -> {
        Log.d(TAG, "onSeekCompleteListener : ");
    });

    private MediaPlayer.OnInfoListener onInfoListener = ((mediaPlayer, what, extra) -> {
        Log.d(TAG, "onInfoListener : ");
        return false;
    });


    /**
     * 단말기의 상태 파악하여 mediaplayer 컨트롤
     */
    @SuppressLint("ServiceCast")
    private void callStateListener() {

        // TelephonyManager 이용하기 위해서는 manifest에 아래 퍼미션 추가 필요
        //<uses-permission android:name="android.permission.READ_PHONE_STATE" />
        telephonyManager = (TelephonyManager) getSystemService(Context.TELECOM_SERVICE);

        phoneStateListener = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String phoneNumber) {
                super.onCallStateChanged(state, phoneNumber);

                switch (state) {
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                    case TelephonyManager.CALL_STATE_RINGING:
                        pauseMedia();
                        ongoingCall = true;
                        break;
                    case TelephonyManager.CALL_STATE_IDLE:
                        if (ongoingCall) {
                            ongoingCall = false;
                            resumeMedia();
                        }
                }
            }
        };
    }


    private void pauseMedia() {
        if (currentPlayer != null) {
            if (currentPlayer.isPlaying()) currentPlayer.pause();
        }
    }

    private void resumeMedia() {
        if (currentPlayer != null) {
            if (!currentPlayer.isPlaying()) {
                currentPlayer.start();
            }
        }
    }


    /**
     * 이어폰을 뽑은 경우에 대한 컨트롤
     */
    private void registerRemoveEarphone() {
        // AudioManager.RINGER_MODE_NORMAL  : 벨 모드일 경우
        // AudioManager.RINGER_MODE_SILENT  :  사일런트 모드일 경우
        // AudioManager.RINGER_MODE_VIBRATE  :  진동모드일 경우
        // AudioManager.ACTION_AUDIO_BECOMING_NOISY : 이어잭을 꼽고 있다가 뺏을 경우
        IntentFilter intentFilter = new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
        registerReceiver(removeEarphone, intentFilter);

    }

    private BroadcastReceiver removeEarphone = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            pauseMedia();
        }
    };

    private void register_AudioControl() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(SoundTherapyBroadcastIntent.BROADCAST_PLAY_SOUNDTHERAPY);
        filter.addAction(SoundTherapyBroadcastIntent.BROADCAST_PAUSE_SOUNDTHERAPY);
        filter.addAction(SoundTherapyBroadcastIntent.BROADCAST_RESUME_SOUNDTHERAPY);
        filter.addAction(SoundTherapyBroadcastIntent.BROADCAST_STOP_SOUNDTHERAPY);
        filter.addAction(SoundTherapyBroadcastIntent.BROADCAST_DESTROY_SOUNDTHERAPY);
        filter.addAction(SoundTherapyBroadcastIntent.BROADCAST_HIDEMINIPLAYER_SOUNDTHERAPY);
        filter.addAction(SoundTherapyBroadcastIntent.BROADCAST_ONCEPLAY_SOUNDTHERAPY);
        filter.addAction(SoundTherapyBroadcastIntent.BROADCAST_REPEATMODE_SOUNDTHERAPY);
        registerReceiver(controlAudio, filter);

    }

    private BroadcastReceiver controlAudio = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            switch (intent.getAction()) {
                case SoundTherapyBroadcastIntent.BROADCAST_PLAY_SOUNDTHERAPY:

                    break;
                case SoundTherapyBroadcastIntent.BROADCAST_PAUSE_SOUNDTHERAPY:

                    break;
                case SoundTherapyBroadcastIntent.BROADCAST_RESUME_SOUNDTHERAPY:

                    break;
                case SoundTherapyBroadcastIntent.BROADCAST_STOP_SOUNDTHERAPY:

                    break;
                case SoundTherapyBroadcastIntent.BROADCAST_DESTROY_SOUNDTHERAPY:

                    break;
                case SoundTherapyBroadcastIntent.BROADCAST_HIDEMINIPLAYER_SOUNDTHERAPY:

                    break;
                case SoundTherapyBroadcastIntent.BROADCAST_ONCEPLAY_SOUNDTHERAPY:

                    break;
                case SoundTherapyBroadcastIntent.BROADCAST_REPEATMODE_SOUNDTHERAPY:

                    break;
                default:
                    break;
            }

        }
    };


    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onAudioFocusChange(int focusState) {

        switch (focusState) {
            case AudioManager.AUDIOFOCUS_GAIN:
                // resume playback
                break;
            case AudioManager.AUDIOFOCUS_LOSS:
                // Lost focus for an unbounded amount of time: stop playback and release media player
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                // Lost focus for a short time, but we have to stop
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                // Lost focus for a short time, but it's ok to keep playing
                pauseMedia();
                break;

        }

    }

    private boolean requestAudioFocus() {
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        int result = audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            //focus gained
            return true;
        } else {
            //focus not gained
            return false;
        }
    }


    public class LocalBinder extends Binder {

        public MusicPlayerService getService() {
            return MusicPlayerService.this;
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        unregisterReceiver(removeEarphone);
        unregisterReceiver(controlAudio);
    }
}
