package com.jinhanexample.scrollview.appBarHide;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.jinhanexample.viewPager.depth.fragment.FirstFragment;
import com.jinhanexample.viewPager.depth.fragment.SecondFragment;
import com.jinhanexample.viewPager.depth.fragment.ThirdFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private String[] dataList;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, String[] dataList) {
        super(fragmentActivity);
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new FirstFragment();
                break;
            case 1:
                fragment = new SecondFragment();
                break;
            case 2:
                fragment = new ThirdFragment();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return dataList.length;
    }


}
