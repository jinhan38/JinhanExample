package com.jinhanexample.scrollview.appBarHide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.jinhanexample.databinding.AppBarHideAnimationFragmentBinding;

public class AppBarHideAnimationFragment extends Fragment {

    private AppBarHideAnimationFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = AppBarHideAnimationFragmentBinding.inflate(getLayoutInflater());

        String[] dataList = new String[]{"First", "Second", "Third"};

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity(), dataList);
        binding.viewPager.setAdapter(viewPagerAdapter); // note : It is ViewPager2, Not Viewpager

        //This is from official google document.
        //If you want know more about this, please refer to this link :
        //https://developer.android.com/guide/navigation/navigation-swipe-view-2?hl=ko
        new TabLayoutMediator(binding.tabLayout, binding.viewPager, ((tab, position) -> tab.setText("OBJECT " + (position + 1))
        )).attach();

        return binding.getRoot();

    }
}
