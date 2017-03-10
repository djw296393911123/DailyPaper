package com.djw.dailypaper.view.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.djw.dailypaper.R;
import com.djw.dailypaper.adapter.ZhihuFragmentAdapter;
import com.djw.dailypaper.base.BaseFragment;
import com.djw.dailypaper.view.fragment.zhihu.DayPaperFragment;
import com.djw.dailypaper.view.fragment.zhihu.HotFragment;
import com.djw.dailypaper.view.fragment.zhihu.ThemFragment;
import com.djw.dailypaper.view.fragment.zhihu.ZhulanFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhihuFragment extends BaseFragment {


    private ViewPager pager;
    private TabLayout tabLayout;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initView(View view) {
        pager = ((ViewPager) view.findViewById(R.id.vp_zhihu));
        tabLayout = ((TabLayout) view.findViewById(R.id.tl_zhihu));
    }

    @Override
    protected void doBusiness() {
        fragments.add(new DayPaperFragment());
        fragments.add(new ThemFragment());
        fragments.add(new ZhulanFragment());
        fragments.add(new HotFragment());
        pager.setAdapter(new ZhihuFragmentAdapter(getChildFragmentManager(),fragments));
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_zhihu;
    }

}
