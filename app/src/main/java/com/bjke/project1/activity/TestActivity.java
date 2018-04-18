package com.bjke.project1.activity;

import android.view.View;
import android.widget.TextView;

import com.bjke.project1.R;
import com.bjke.project1.base.BaseActivity;
import com.bjkj.library.greendao.BjkjDao;
import com.bjkj.library.greendao.DaoManager;
import com.bjkj.library.greendao.bean.CustromBean;
import com.bjkj.library.greendao.manager.CustromManager;
import com.bjkj.library.utils.LogUtils;

/**
 * Created by liyou on 2018/4/4.
 */

public class TestActivity extends BaseActivity implements View.OnClickListener{
    private TextView mAdd;
    private TextView mQuery;
    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void initView() {
        mAdd = findViewById(R.id.add);
        mQuery = findViewById(R.id.query);
    }

    @Override
    public void initListener() {
        super.initListener();
        mAdd.setOnClickListener(this);
        mQuery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                CustromBean bean = new CustromBean();
                bean.setUserId("1");
               bean.setUserName("bjkj");
                DaoManager.getInstance().setCustrom(bean);
                break;
            case R.id.query:
               CustromBean bean1= DaoManager.getInstance().getCustrom("1");
                LogUtils.e("name:"+bean1.getUserName());
                break;
        }
    }
}
