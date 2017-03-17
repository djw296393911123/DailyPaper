package com.djw.dailypaper.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.djw.dailypaper.R;
import com.djw.dailypaper.adapter.ZhuanlanInfoAdapter;
import com.djw.dailypaper.base.BaseActivity;
import com.djw.dailypaper.contracts.ZhuanlanInfoContracts;
import com.djw.dailypaper.model.data.gank.ZhuanlanInfoData;
import com.djw.dailypaper.presenter.ZhuanlanInfoPresenter;

public class ZhuanlanActivity extends BaseActivity implements ZhuanlanInfoContracts.View {

    private ZhuanlanInfoAdapter adapter;

    @Override
    public void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_zhuanlan_info);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ZhuanlanInfoAdapter(this);
        recyclerView.setAdapter(adapter);
        new ZhuanlanInfoPresenter(this).getDataFromModel(getIntent().getStringExtra("id"));
    }

    @Override
    public void doBusiness() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuanlan);
    }

    @Override
    public void setPresenter(ZhuanlanInfoContracts.Presenter presenter) {

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

    }

    @Override
    public void getThemData(ZhuanlanInfoData data) {
        adapter.notifyListChange(data.getStories());
    }
}
