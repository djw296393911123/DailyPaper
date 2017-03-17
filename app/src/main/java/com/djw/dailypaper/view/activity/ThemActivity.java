package com.djw.dailypaper.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

public class ThemActivity extends BaseActivity implements ThemInfoContracts.View {

    private ThemInfoAdapter adapter;
    private ImageView head;
    private Toolbar toolbar;

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
        adapter.notifyListChange(data.getStories());
        Glide.with(this).load(data.getBackground()).asBitmap().into(head);
        toolbar.setTitle(data.getDescription());
    }
}
