package com.bjke.project1.activity;

import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.ImageView;

import com.bjke.project1.R;
import com.bjke.project1.base.BaseActivity;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by liyou on 2018/3/29.
 */

public class WebBackActivity extends BaseActivity {
    private WebView mWebView;
    private ImageView mBack;
    private String url = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public void initView() {
        mWebView = findViewById(R.id.webView);
        mBack = findViewById(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        url = getIntent().getStringExtra("url");
        initWebViewSettings();
        mWebView.setWebViewClient(webViewClient);
        mWebView.setWebChromeClient(webChromeClient);
        mWebView.addJavascriptInterface(new SsqBet(), "ZstBet");
        mWebView.loadUrl(url);
    }

    private void initWebViewSettings() {
        WebSettings webSetting = mWebView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);

    }

    private WebViewClient webViewClient = new WebViewClient() {


        @Override
        public void onPageFinished(WebView webView, String s) {
            super.onPageFinished(webView, s);

            String str = "javascript:var adDiv=document.getElementsByClassName('bg')[0];adDiv.style.backgroundColor='#f39800';";
//            mWebView.loadUrl( str);
            mWebView.evaluateJavascript(str, new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String s) {

                }
            });

//            String str2 = "javascript:var adDiv=document.getElementsByClassName('title')[0];adDiv.style.backgroundColor='#f39800';";
////            mWebView.loadUrl( str);
//            mWebView.evaluateJavascript(str2, new ValueCallback<String>() {
//                @Override
//                public void onReceiveValue(String s) {
//
//                }
//            });

        }

        public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString) {
//            Log.e("urlllll", paramString);

            return true;
        }

    };

    private WebChromeClient webChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
//            if (newProgress == 100) {
//                WebProgressbar.setProgress(100);
//                WebProgressbar.setVisibility(View.INVISIBLE);
//            } else {
//                if (View.INVISIBLE == WebProgressbar.getVisibility()) {
//                    WebProgressbar.setVisibility(View.VISIBLE);
//                }
//                WebProgressbar.setProgress(newProgress);
//            }
            super.onProgressChanged(view, newProgress);
        }
    };

    public class SsqBet {
        @JavascriptInterface
        public void back() {
            finish();
        }
    }
}
