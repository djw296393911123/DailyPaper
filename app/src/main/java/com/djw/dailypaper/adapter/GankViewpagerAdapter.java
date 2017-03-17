package com.djw.dailypaper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by JasonDong on 2017/3/16.
 */

public class GankViewpagerAdapter extends FragmentPagerAdapter {

    private String[] titls = {"ANDROID", "IOS", "前端", "福利"};
    private List<Fragment> fragments;

    public GankViewpagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titls.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titls[position];
    }
}
