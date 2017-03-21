package com.djw.dailypaper.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.djw.dailypaper.R;
import com.djw.dailypaper.adapter.ThemInfoAdapter;
import com.djw.dailypaper.base.BaseActivity;
import com.djw.dailypaper.contracts.ThemInfoContracts;
import com.djw.dailypaper.model.data.Them.ThemInfoData;
import com.djw.dailypaper.presenter.ThemInfoPresenter;

import java.util.Collections;
import java.util.List;

public class ThemActivity extends BaseActivity implements ThemInfoContracts.View {

    private ThemInfoAdapter adapter;
    private ImageView head;
    private Toolbar toolbar;
    private List<ThemInfoData.StoriesBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
    }

    @Override
    public void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_them);
        head = (ImageView) findViewById(R.id.iv_head);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ThemInfoAdapter(this);
        recyclerView.setAdapter(adapter);
        new ThemInfoPresenter(this).getDataFromModel(getIntent().getStringExtra("id"));
        //为RecycleView绑定触摸事件
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                //首先回调的方法 返回int表示是否监听该方向
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;//拖拽
                int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;//侧滑删除
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

    @Override
    public void doBusiness() {

    }

    @Override
    public void setPresenter(ThemInfoContracts.Presenter presenter) {

    }

    @Override
    public void hideProgress() {
        dismissProgress();
    }

    @Override
    public void showFail() {

    }

    @Override
    public void showComplete() {

    }

    @Override
    public void showDataEmpty() {
        Toast.makeText(context, "没有更多数据", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getThemData(ThemInfoData data) {
        list = data.getStories();
        adapter.notifyListChange(data.getStories());
        Glide.with(this).load(data.getBackground()).asBitmap().into(head);
        toolbar.setTitle(data.getDescription());
    }
}
