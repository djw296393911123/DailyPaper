package com.djw.dailypaper.view.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.djw.dailypaper.R;
import com.djw.dailypaper.base.BaseActivity;
import com.djw.dailypaper.contracts.WebviewContracts;
import com.djw.dailypaper.model.data.WebviewData;
import com.djw.dailypaper.presenter.WebviewPresenter;
import com.djw.dailypaper.util.HtmlUtil;

public class WebviewActivity extends BaseActivity implements WebviewContracts.View {

    private WebviewPresenter presenter;
    private WebView webView;
    private ImageView head;
    private Toolbar toolbar;

    public final static String CSS_STYLE = "<style>* {font-size:16px;line-height:20px;} p {color:#666666;  padding:3px;} a {color:#3E62A6;} img {max-width:310px;}pre {font-size:9pt;line-height:12pt;font-family:Courier New,Arial;border:1px solid #ddd;border-left:5px solid #6CE26C;background:#f6f6f6;padding:5px;}</style>";

    @Override
    public void initView() {
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
        toolbar.setTitle(data.getTitle());
        Glide.with(this).load(data.getImage()).asBitmap().into(head);
        webView.loadData(CSS_STYLE + data.getBody(), HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
    }
}
