package com.rc.robincollet.weathertest.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

/**
 * Created by robincollet on 20/08/2016.
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragments ;
    public FragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void removeView (Fragment fragment)
    {
        fragments.remove(fragment);
    }
}
