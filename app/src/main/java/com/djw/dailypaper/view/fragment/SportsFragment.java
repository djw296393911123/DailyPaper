package com.djw.dailypaper.view.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.djw.dailypaper.R;
import com.djw.dailypaper.adapter.SportsViewpagerAdapter;
import com.djw.dailypaper.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SportsFragment extends BaseFragment {

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initView(View view) {
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tl_sports);
        ViewPager pager = (ViewPager) view.findViewById(R.id.vp_sports);
        pager.setAdapter(new SportsViewpagerAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    protected void doBusiness() {

    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_sports;
    }

}
