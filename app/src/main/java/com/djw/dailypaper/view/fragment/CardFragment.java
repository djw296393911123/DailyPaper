package com.djw.dailypaper.view.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.djw.dailypaper.R;
import com.djw.dailypaper.adapter.CardAdapter;
import com.djw.dailypaper.base.BaseFragment;
import com.djw.dailypaper.model.data.gank.AndroidData;
import com.djw.dailypaper.retrofit.GankUtil;
import com.mcxtzhang.layoutmanager.swipecard.CardConfig;
import com.mcxtzhang.layoutmanager.swipecard.OverLayCardLayoutManager;
import com.mcxtzhang.layoutmanager.swipecard.RenRenCallback;

import java.util.Collections;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardFragment extends BaseFragment {

    private CardAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_card);
        recyclerView.setLayoutManager(new OverLayCardLayoutManager());
        CardConfig.initConfig(getActivity());
        adapter = new CardAdapter(getActivity());
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void doBusiness() {
        initData();
    }

    public void initData() {
        GankUtil.getDefault().getMeizi("1").observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Subscriber<AndroidData>() {
            private List<AndroidData.ResultsBean> list;

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(AndroidData androidData) {
                list = androidData.getResults();
                adapter.notifyListChange(list);
//                ItemTouchHelper.Callback callback = new RenRenCallback(recyclerView, adapter, androidData.getResults());
//                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
//                itemTouchHelper.attachToRecyclerView(recyclerView);
                //为RecycleView绑定触摸事件
                ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
                    @Override
                    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                        //首先回调的方法 返回int表示是否监听该方向
                        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;//拖拽
                        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN;//侧滑删除
                        return makeMovementFlags(dragFlags, swipeFlags);
                    }

                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        //滑动事件
                        Collections.swap(list, viewHolder.getAdapterPosition(), target.getAdapterPosition());
                        adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        //侧滑事件
                        list.remove(viewHolder.getAdapterPosition());
                        adapter.notifyListChange(list);
                    }

                    @Override
                    public boolean isLongPressDragEnabled() {
                        //是否可拖拽
                        return true;
                    }
                });
                helper.attachToRecyclerView(recyclerView);
            }
        });
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_card;
    }

}
