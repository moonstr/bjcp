package com.bjkj.library.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.bjkj.library.utils.StatusBarUtil;
import com.bjkj.library.view.ComProgressDialog;

/**
 * Created by liyou on 2018/3/28.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private ComProgressDialog loadingDialog;
    public Activity mActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(getLayoutId());
        mActivity=this;
        initView();
        initData();
        initListener();
        setStatusBar();
    }
    public abstract int getLayoutId();
    protected void setStatusBar() {
        StatusBarUtil.setColor(this, Color.parseColor("#FFAD01"));
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    protected void showProgressDialog(int msg) {
        showLoadingDialog(getString(msg));
    }
    protected void showLoadingDialog(String msg){
            if (loadingDialog==null){
                loadingDialog= new ComProgressDialog.Builder(mActivity).setMessage(msg).create();
            }else {
                loadingDialog.setMessage(msg);
            }
        loadingDialog.show();
    }

    public void hideLoadingDialog(){
        if (loadingDialog!=null&&loadingDialog.isShowing()){
            loadingDialog.dismiss();
        }

    }

    public abstract void  initView();
    public  void  initData(){}
    public  void  initListener(){}
}
