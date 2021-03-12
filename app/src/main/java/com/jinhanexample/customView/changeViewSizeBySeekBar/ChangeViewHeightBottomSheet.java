package com.jinhanexample.customView.changeViewSizeBySeekBar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.view.Display;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.jinhanexample.R;

import java.util.Timer;
import java.util.TimerTask;

public class ChangeViewHeightBottomSheet {

    private BottomSheetBehavior behavior;
    private Activity activity;

    private int screenHeight = 0;

    private LinearLayout llDefaultView;
    private LinearLayout llChangeView;
    private SeekBar seekBar;
    private TextView tvProgressNum;


    public ChangeViewHeightBottomSheet(BottomSheetBehavior behavior, Activity activity) {
        this.behavior = behavior;
        this.activity = activity;
        llDefaultView = activity.findViewById(R.id.llDefaultView);
        llChangeView = activity.findViewById(R.id.llChangeView);
        seekBar = activity.findViewById(R.id.seekBar);
        tvProgressNum = activity.findViewById(R.id.tvProgressNum);

        //get display height
        Display display = activity.getWindowManager().getDefaultDisplay();
        this.screenHeight = display.getHeight();
    }


    /**
     * Show this bottomSheet
     *
     * @param color
     * @param seekBarProgress
     */
    public void show(int color, int seekBarProgress) {

        llDefaultView.setBackgroundColor(color);
        llChangeView.setBackgroundColor(color);
        llChangeView.getLayoutParams().height = 0;
        llChangeView.requestLayout();
        seekBar.setProgress(seekBarProgress);

        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        changeSeekBar();
    }


    /**
     * seekBar lisetenr
     */
    public void changeSeekBar() {

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvProgressNum.setText(String.valueOf(progress));
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                Handler handler = new Handler();
                new Thread(() -> {

                    long maxHeight = screenHeight;
                    long increaseCount = maxHeight / 150;

                    long maxNum = 150;
                    boolean loopEnd = false;
                    long increaseMount = 1;
                    long status = 0;
                    while (!loopEnd) {
                        status += increaseMount;
                        if (status > maxNum) {
                            status = maxNum;
                            loopEnd = true;
                        }

                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        int count = (int) (status * increaseCount);

                        handler.post(() -> {
                            llChangeView.getLayoutParams().height = count;
                            llChangeView.requestLayout();
                        });
                    }


                }).start();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                hidden();
            }
        });
    }


    /**
     * Hidden this bottomSheet
     */
    public void hidden() {

        Handler handler = new Handler();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> behavior.setState(BottomSheetBehavior.STATE_HIDDEN));
            }
        }, 200);


    }
}
