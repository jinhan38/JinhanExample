package com.jinhanexample.customView.changeViewSizeBySeekBar;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.jinhanexample.R;
import com.jinhanexample.databinding.ActivityChangeViewSizeBySeekBarBinding;

public class ChangeViewSizeBySeekBarJava extends AppCompatActivity {


    private ActivityChangeViewSizeBySeekBarBinding b;
    private View viewBottomSheet;
    private BottomSheetBehavior behavior;
    private ChangeViewHeightBottomSheet changeViewHeightBottomSheet;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        b = ActivityChangeViewSizeBySeekBarBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        viewBottomSheet = findViewById(R.id.custom_change_vertical_size_view);
        behavior = (BottomSheetBehavior) BottomSheetBehavior.from(viewBottomSheet);
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        changeViewHeightBottomSheet = new ChangeViewHeightBottomSheet(behavior, this);

        b.tvShowBottomSheet.setOnClickListener(view -> {
            changeViewHeightBottomSheet.show(getResources().getColor(R.color.brand_purple), 0);
        });

    }

}
