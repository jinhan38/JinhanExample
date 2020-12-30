package com.jinhanexample.viewControl.layoutParams;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jinhanexample.databinding.ActivityLayoutParamsBinding;

public class LayoutParamsActivityJava extends AppCompatActivity {

    private ActivityLayoutParamsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLayoutParamsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(v -> {
            changeTextViewSize();
        });

    }


    private void changeTextViewSize() {

        ViewGroup.LayoutParams params = binding.tvFirst.getLayoutParams();
        params.width = getDP(this, 250);
        binding.tvFirst.setLayoutParams(params);


        LinearLayout.LayoutParams marginParams = (LinearLayout.LayoutParams) binding.tvSecond.getLayoutParams();
        marginParams.setMargins(30, 300, 0, 0);
        binding.tvSecond.setLayoutParams(marginParams);


        FrameLayout.LayoutParams thirdParams = new FrameLayout.LayoutParams(
                getDP(this, 250),
                getDP(this, 100)
        );
        thirdParams.setMargins(50, 50, 0, 0);
        binding.tvThird.setLayoutParams(thirdParams);

    }


    public int getDP(Context context, int value) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                value,
                context.getResources().getDisplayMetrics());
    }
}
