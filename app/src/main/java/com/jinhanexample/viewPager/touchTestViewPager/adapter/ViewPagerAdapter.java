package com.jinhanexample.viewPager.touchTestViewPager.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.jinhanexample.viewPager.touchTestViewPager.ui.TouchTestViewPagerFragment;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragmentLists = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> fragmentLists) {
        super(fm);
        this.fragmentLists = fragmentLists;
    }




    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentLists.get(position);
    }

    @Override
    public int getCount() {
        return fragmentLists.size();
    }




}
