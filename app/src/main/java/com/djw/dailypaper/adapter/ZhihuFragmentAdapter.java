package com.djw.dailypaper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class ZhihuFragmentAdapter extends FragmentPagerAdapter {

    private String[] titles = {"日报", "主题", "专栏", "热门"};

    private List<Fragment> fragments;

    public ZhihuFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
