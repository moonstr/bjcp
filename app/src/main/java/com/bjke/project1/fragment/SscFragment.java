package com.bjke.project1.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bjke.project1.R;
import com.bjke.project1.activity.QuickDetailActivity;
import com.bjke.project1.adapter.QuickAdapter;
import com.bjke.project1.adapter.SSCAdapter;
import com.bjke.project1.base.BaseFragment;
import com.bjke.project1.bean.QuickBean;
import com.bjke.project1.bean.SSCBean;
import com.bjke.project1.modle.SSCModle;
import com.bjkj.library.okhttp.HttpCallBack;
import com.bjkj.library.utils.SPUtils;
import com.google.gson.Gson;

/**
 * Created by liyou on 2018/4/10.
 */

public class SscFragment extends BaseFragment{
    ListView mList;
    SSCAdapter sscAdapter;
    SSCBean sscBean;
    QuickAdapter quickAdapter;
    QuickBean quickBean;
    @Override
    public int getContentViewLayoutId() {
        return R.layout.fragment_ssc;
    }

    @Override
    public void initView() {
        mList = findViewById(R.id.list);

    }

    private void setSSCAdapter(){
        sscAdapter = new SSCAdapter(mActivity,sscBean);
        mList.setAdapter(sscAdapter);
        SSCModle.getSSCName(new HttpCallBack<SSCBean>() {
            @Override
            public void success(SSCBean bean) {
                sscBean=bean;
                sscAdapter.setData(bean);
            }

            @Override
            public void fail(String errorStr) {

            }
        });
    }

    private void setQuickAdapter(){
        quickAdapter = new QuickAdapter(mActivity,quickBean);
        mList.setAdapter(quickAdapter);
        SSCModle.getQuickName(new HttpCallBack<QuickBean>() {
            @Override
            public void success(QuickBean bean) {
                quickBean = bean;
                quickAdapter.setData(bean);
            }

            @Override
            public void fail(String errorStr) {

            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        quickBean = getDataFromLocal();
//        setSSCAdapter();
        setQuickAdapter();
    }

    @Override
    public void initListener() {
        super.initListener();
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mActivity, QuickDetailActivity.class);
                intent.putExtra("title",quickBean.data.get(position).game_name);
                intent.putExtra("tag",quickBean.data.get(position).tag);
                startActivity(intent);
            }
        });
    }

    private QuickBean getDataFromLocal(){
        Gson gson=new Gson();

        QuickBean bean=gson.fromJson((String)SPUtils.get("getQuickName",""),QuickBean.class) ;
        return bean;
    }
}
