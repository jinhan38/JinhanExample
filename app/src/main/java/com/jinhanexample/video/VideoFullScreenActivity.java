package com.jinhanexample.video;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jinhanexample.databinding.ActivityVideoFullScreenBinding;

public class VideoFullScreenActivity extends AppCompatActivity {

    private ActivityVideoFullScreenBinding b;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityVideoFullScreenBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());




    }
}
