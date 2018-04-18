package com.bjke.project1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bjke.project1.R;
import com.bjke.project1.activity.WebActivity;
import com.bjke.project1.adapter.HotAdapter;
import com.bjke.project1.base.BaseFragment;
import com.bjke.project1.bean.HotBean;
import com.bjke.project1.modle.LotteryModle;
import com.bjkj.library.okhttp.HttpCallBack;

/**
 * Created by liyou on 2018/4/3.
 */

public class HotChildFragment extends BaseFragment{
    private ListView mList;
    private HotAdapter adapter;
    HotBean hotBean;
    public HotChildFragment(){}
    public static HotChildFragment newInstance(String url){
        HotChildFragment fragment = new HotChildFragment();
        Bundle bundle = new Bundle();
        bundle.putString( "url", url);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public int getContentViewLayoutId() {
        return R.layout.fragment_hotdetail;
    }

    @Override
    public void initView() {
        mList = findViewById(R.id.list);
    }

    @Override
    public void initData() {
        super.initData();

        adapter =new HotAdapter(hotBean,mActivity);
        mList.setAdapter(adapter);
        LotteryModle.getHotData(getArguments().getString("url"),new HttpCallBack<HotBean>() {
            @Override
            public void success(HotBean bean) {
                hotBean=bean;
                adapter.setData(hotBean);
            }

            @Override
            public void fail(String errorStr) {

            }
        });
    }

    @Override
    public void initListener() {
        super.initListener();

                mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(mActivity, WebActivity.class);
                intent.putExtra("url",hotBean.dataList.get(i).url);
                startActivity(intent);
            }
        });
    }
}
