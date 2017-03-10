package com.djw.dailypaper.view.activity;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.WindowManager;

import com.djw.dailypaper.R;
import com.djw.dailypaper.base.BaseActivity;
import com.djw.dailypaper.contracts.WebviewContracts;
import com.djw.dailypaper.model.data.WebviewData;
import com.djw.dailypaper.presenter.WebviewPresenter;
import com.djw.dailypaper.util.HtmlUtil;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class WebviewActivity extends BaseActivity implements WebviewContracts.View {

    private WebView webView;
    private WebviewPresenter presenter;

    @Override
    public void initView() {
        webView = (WebView) findViewById(R.id.wv_tencent);
        WebSettings settings = webView.getSettings();
//        settings.setBlockNetworkImage(true);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        presenter = new WebviewPresenter(this);
    }

    @Override
    public void doBusiness() {
        presenter.getDataFromModel(String.valueOf(getIntent().getIntExtra("id", -1)));
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
    public void getWebviewData(WebviewData data) {
        webView.loadData(data.getBody(), HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
    }
}
