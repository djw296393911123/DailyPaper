package com.djw.dailypaper.view.activity;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.dailypaper.R;
import com.djw.dailypaper.base.BaseActivity;
import com.djw.dailypaper.contracts.WebviewContracts;
import com.djw.dailypaper.model.data.OtherData;
import com.djw.dailypaper.model.data.WebviewData;
import com.djw.dailypaper.presenter.WebviewPresenter;
import com.djw.dailypaper.util.HtmlUtil;
import com.djw.dailypaper.util.ShareUtil;

public class WebviewActivity extends BaseActivity implements WebviewContracts.View, View.OnClickListener {

    private WebviewPresenter presenter;
    private WebView webView;
    private ImageView head;
    private Toolbar toolbar;

    public final static String CSS_STYLE = "<style>* {font-size:16px;line-height:20px;} p {color:#666666;  padding:3px;} a {color:#3E62A6;} img {max-width:310px;}pre {font-size:9pt;line-height:12pt;font-family:Courier New,Arial;border:1px solid #ddd;border-left:5px solid #6CE26C;background:#f6f6f6;padding:5px;}</style>";
    private TextView pinglun;
    private TextView zan;
    private String share_url;
    private LinearLayout bottom;
    private boolean isBottomShow = true;
    private NestedScrollView scrollView;

    @Override
    public void initView() {
        bottom = (LinearLayout) findViewById(R.id.ll_bottom);
        scrollView = (NestedScrollView) findViewById(R.id.nsv_web);
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY - oldScrollY > 0 && isBottomShow) {  //下移隐藏
                    isBottomShow = false;
                    bottom.animate().translationY(scrollView.getHeight());
                } else if (scrollY - oldScrollY < 0 && !isBottomShow) {    //上移出现
                    isBottomShow = true;
                    bottom.animate().translationY(0);
                }
            }
        });
        LinearLayout share = (LinearLayout) findViewById(R.id.ll_three);
        share.setOnClickListener(this);
        pinglun = (TextView) findViewById(R.id.tv_pinglun);
        zan = (TextView) findViewById(R.id.tv_zan);
        webView = (WebView) findViewById(R.id.wv_web);
        head = (ImageView) findViewById(R.id.iv_head);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        presenter = new WebviewPresenter(this);
    }

    @Override
    public void doBusiness() {
        presenter.getDataFromModel(getIntent().getStringExtra("id"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
    }

    @Override
    public void setPresenter(WebviewContracts.Presenter presenter) {

    }

    @Override
    public void hideProgress() {

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
    public void getWebviewData(WebviewData data) {
        share_url = data.getShare_url();
        toolbar.setTitle(data.getTitle());
        Glide.with(this).load(data.getImage()).asBitmap().into(head);
        webView.loadData(CSS_STYLE + data.getBody(), HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
        presenter.getInfo(getIntent().getStringExtra("id"));
    }

    @Override
    public void getInfo(OtherData data) {
        Log.i("zhihuinfodeata====>>", data.toString());
        zan.setText(data.getPopularity());
        pinglun.setText(data.getComments());
    }

    @Override
    public void onClick(View v) {
        ShareUtil.shareText(context, share_url, "分享一篇文章");
    }
}
