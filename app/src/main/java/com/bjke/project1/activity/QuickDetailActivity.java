package com.bjke.project1.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjke.project1.R;
import com.bjke.project1.base.BaseActivity;
import com.bjke.project1.view.TitleView;

/**
 * Created by liyou on 2018/4/11.
 */

public class QuickDetailActivity extends BaseActivity{
    private ImageView mBack;
    private TitleView mTitle;
    private TextView mSearch;
    private TextView mTrend;
    @Override
    public int getLayoutId() {
        return R.layout.activity_quickdetail;
    }

    @Override
    public void initView() {
        mBack = findViewById(R.id.back);
        mTitle =findViewById(R.id.title);
        mSearch = findViewById(R.id.search);
        mTrend = findViewById(R.id.trend);
    }

    @Override
    public void initData() {
        super.initData();
        mTitle.setTitle(getIntent().getStringExtra("title"));
    }

    @Override
    public void initListener() {
        super.initListener();
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity,QuickLotteryActivity.class);
                intent.putExtra("tag",getIntent().getStringExtra("tag"));
                startActivity(intent);
            }
        });

        mTrend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity,QuickTrendActivity.class);
                intent.putExtra("tag",getIntent().getStringExtra("tag"));
                startActivity(intent);
            }
        });
    }
}
