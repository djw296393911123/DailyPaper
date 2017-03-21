package com.djw.dailypaper.view.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.djw.dailypaper.R;
import com.djw.dailypaper.adapter.GankViewpagerAdapter;
import com.djw.dailypaper.base.BaseFragment;
import com.djw.dailypaper.view.fragment.gank.AllFragment;
import com.djw.dailypaper.view.fragment.gank.AndroidFragment;
import com.djw.dailypaper.view.fragment.gank.IosFragment;
import com.djw.dailypaper.view.fragment.gank.MeiziFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GankFragment extends BaseFragment {

    private ViewPager pager;
    private TabLayout tabLayout;

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initView(View view) {
        pager = ((ViewPager) view.findViewById(R.id.vp_gank));
        tabLayout = ((TabLayout) view.findViewById(R.id.tl_gank));
    }

    @Override
    protected void doBusiness() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new AndroidFragment());
        fragments.add(new IosFragment());
        fragments.add(new AllFragment());
        fragments.add(new MeiziFragment());
        pager.setAdapter(new GankViewpagerAdapter(getChildFragmentManager(), fragments));
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_gank;
    }

    public void setPager(int position) {
        if (pager != null)
            pager.setCurrentItem(position);
    }

}
