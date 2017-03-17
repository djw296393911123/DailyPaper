package com.djw.dailypaper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.djw.dailypaper.view.fragment.sports.SportFragment;

import java.util.List;

/**
 * Created by JasonDong on 2017/3/16.
 */

public class SportsViewpagerAdapter extends FragmentPagerAdapter {

    private String[] titls = {"NBA", "足球", "娱乐", "妹子"};

    public SportsViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return SportFragment.newInstance(position);
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
