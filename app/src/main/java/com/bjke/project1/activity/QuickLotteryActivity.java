package com.bjke.project1.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.bjke.project1.R;
import com.bjke.project1.adapter.QuickLotteryAdpter;
import com.bjke.project1.base.BaseActivity;
import com.bjke.project1.bean.QuickLotteryBean;
import com.bjke.project1.modle.SSCModle;
import com.bjkj.library.okhttp.HttpCallBack;

/**
 * Created by liyou on 2018/4/11.
 */

public class QuickLotteryActivity extends BaseActivity {
    private ListView mList;
    QuickLotteryAdpter adapter;
    QuickLotteryBean bean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_quicklottery;
    }

    @Override
    public void initView() {
        mList = findViewById(R.id.list);
    }

    @Override
    public void initData() {
        super.initData();
        adapter = new QuickLotteryAdpter(mActivity, bean);
        mList.setAdapter(adapter);
        SSCModle.getQuickLottery(getIntent().getStringExtra("tag"), new HttpCallBack<QuickLotteryBean>() {
            @Override
            public void success(QuickLotteryBean quickBean) {
                bean = quickBean;
                adapter.setData(bean);
            }

            @Override
            public void fail(String errorStr) {

            }
        });
    }

    @Override
    public void initListener() {
        super.initListener();
    }
}
