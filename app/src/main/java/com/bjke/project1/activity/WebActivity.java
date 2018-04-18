package com.bjke.project1.activity;

import com.bjke.project1.R;
import com.bjke.project1.base.BaseActivity;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by liyou on 2018/3/29.
 */

public class WebActivity extends BaseActivity {
    private WebView mWebView;
    private String url="";
    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public void initView() {
        mWebView=findViewById(R.id.webView);

    }

    @Override
    public void initData() {
        super.initData();
        url = getIntent().getStringExtra("url");
        initWebViewSettings();
        mWebView.setWebViewClient(webViewClient);
        mWebView.setWebChromeClient(webChromeClient);
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
            mWebView.loadUrl("javascript:var adDiv=document.getElementsByClassName(\"dgfff\")[0];adDiv.parentNode.removeChild(adDiv)");
            mWebView.loadUrl("javascript:var adDiv=document.getElementsByClassName(\"xgwz\")[0];adDiv.parentNode.removeChild(adDiv)");
            mWebView.loadUrl("javascript:var adDiv=document.getElementsByClassName(\"footer\")[0];adDiv.parentNode.removeChild(adDiv)");
            mWebView.loadUrl("javascript:var adDiv=document.getElementsByTagName(\"iframe\")[0];adDiv.parentNode.removeChild(adDiv)");
            mWebView.loadUrl("javascript:var adDiv=document.getElementsByTagName(\"iframe\")[0];adDiv.parentNode.removeChild(adDiv)");
            mWebView.loadUrl("javascript:var adDiv=document.getElementsByClassName(\"home\")[0];adDiv.parentNode.removeChild(adDiv)");
            mWebView.loadUrl("javascript:var adDiv=document.getElementsByTagName(\"footer\")[0];adDiv.parentNode.removeChild(adDiv)");
            mWebView.loadUrl("javascript:var adDiv=document.getElementsByTagName(\"iframe\")[0];adDiv.parentNode.removeChild(adDiv)");
            mWebView.loadUrl("javascript:var adDiv=document.getElementsByClassName(\"cz\")[0];adDiv.parentNode.removeChild(adDiv)");
            mWebView.loadUrl("javascript:var adDiv=document.getElementsByClassName(\"pic_container\")[0];adDiv.parentNode.removeChild(adDiv)");
        }

        public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
        {
//            Log.e("urlllll", paramString);
            if (!paramString.equals(url))
                WebActivity.this.finish();
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
}
