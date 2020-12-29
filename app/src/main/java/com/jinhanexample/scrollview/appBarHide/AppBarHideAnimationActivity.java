package com.jinhanexample.scrollview.appBarHide;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jinhanexample.R;
import com.jinhanexample.databinding.ActivityAppBarHideAnimationBinding;

public class AppBarHideAnimationActivity extends AppCompatActivity {

    private ActivityAppBarHideAnimationBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        binding = ActivityAppBarHideAnimationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Fragment fragment = new AppBarHideAnimationFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();

    }
}
