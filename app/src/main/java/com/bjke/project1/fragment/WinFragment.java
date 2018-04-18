package com.bjke.project1.fragment;

import com.bjke.project1.R;
import com.bjke.project1.base.BaseFragment;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by liyou on 2018/3/29.
 */

public class WinFragment extends BaseFragment {
    public String url="http://m.sporttery.cn/wap/list.php?s=digital&sort_id=19";
    private WebView mWebView;
    @Override
    public int getContentViewLayoutId() {
        return R.layout.fragment_win;
    }

    @Override
    public void initView() {
        mWebView = findViewById(R.id.webView);
    }

    @Override
    public void initData() {
        super.initData();
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
    @Override
    public void initListener() {
        super.initListener();
    }

    private WebViewClient webViewClient = new WebViewClient() {

        @Override
        public void onPageFinished(WebView webView, String s) {
            super.onPageFinished(webView, s);
            String str = "javascript:var adDiv=document.getElementsByClassName('top-header')[0];adDiv.style.backgroundColor='#f39800';";
//            mWebView.loadUrl( str);
            mWebView.evaluateJavascript(str, new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String s) {

                }
            });
        }

        @Override
        public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {

            webView.loadUrl(s);
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
