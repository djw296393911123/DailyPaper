package com.djw.dailypaper.view.fragment.gank;


import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.djw.dailypaper.R;
import com.djw.dailypaper.adapter.AndroidAdapter;
import com.djw.dailypaper.base.BaseFragment;
import com.djw.dailypaper.contracts.AllContracts;
import com.djw.dailypaper.model.data.gank.AndroidData;
import com.djw.dailypaper.presenter.AllPresenter;

import java.util.Collections;
import java.util.List;

import static com.djw.dailypaper.util.RecyclerViewUtil.isSlideToBottom;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends BaseFragment implements AllContracts.View, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private AndroidAdapter adapter;

    private boolean isSuccess = false;
    private AllPresenter presenter;
    private ImageView head;
    private int index = 1;
    private boolean isLoading = false;

    @Override
    protected void lazyLoad() {
        if (!isVisible || !isSuccess)
            return;
        presenter.getMeizi();
        presenter.getDataFromModel("1");
    }

    @Override
    protected void initView(View view) {
        isSuccess = true;
        head = ((ImageView) view.findViewById(R.id.iv_head));
        swipeRefreshLayout = ((SwipeRefreshLayout) view.findViewById(R.id.srl_all));
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_gank);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (isSlideToBottom(recyclerView) && !isLoading) {
                    presenter.getMore(String.valueOf(++index));
                    isLoading = true;
                }
            }
        });
        adapter = new AndroidAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        presenter = new AllPresenter(this);

        lazyLoad();

    }

    @Override
    protected void doBusiness() {

    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_android;
    }

    @Override
    public void setPresenter(AllContracts.Presenter presenter) {

    }

    @Override
    public void showDataEmpty() {
        Toast.makeText(getActivity(), "没有更多数据", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showError(String s) {
        Toast.makeText(getActivity(), "s", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showFail() {
        isLoading = false;
    }

    @Override
    public void showComplete() {
        isLoading = false;
    }

    @Override
    public void getAll(List<AndroidData.ResultsBean> data) {
        Log.i("data", data.toString());
        adapter.notifyDataChange(data, false);
    }

    @Override
    public void getMeizi(List<AndroidData.ResultsBean> data) {
        Glide.with(getActivity()).load(data.get(0).getUrl()).asBitmap().into(head);
    }

    @Override
    public void getMore(List<AndroidData.ResultsBean> data) {
        adapter.notifyDataChange(data, true);
    }

    @Override
    public void onRefresh() {
        presenter.getDataFromModel("1");
        presenter.getMeizi();
        index = 1;
    }
}
