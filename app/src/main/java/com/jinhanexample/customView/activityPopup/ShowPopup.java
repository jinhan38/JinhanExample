package com.jinhanexample.customView.activityPopup;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jinhanexample.databinding.PopupActivityLayoutBinding;

public class ShowPopup extends AppCompatActivity {

    private PopupActivityLayoutBinding b;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        b = PopupActivityLayoutBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        //If you want to fix orientation, input this code
        try {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } catch (IllegalStateException ignore) {

        }

        b.tvOK.setOnClickListener(view -> finish());

    }
}
