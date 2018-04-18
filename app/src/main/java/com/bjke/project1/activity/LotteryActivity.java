package com.bjke.project1.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bjke.project1.R;
import com.bjke.project1.adapter.LotterAdapter;
import com.bjke.project1.base.BaseActivity;
import com.bjke.project1.bean.LotteryBean;
import com.bjke.project1.modle.LotteryModle;
import com.bjke.project1.view.TitleView;
import com.bjkj.library.okhttp.HttpCallBack;

/**
 * Created by liyou on 2018/3/28.
 */

public class LotteryActivity extends BaseActivity {
    String[] strs=new String[]{"双色球","大乐透","福彩3D","排列三","排列五","七星彩"};
    private ListView mList;
    private TitleView mTitle;
    LotterAdapter adapter;
    LotteryBean bean;
    int type=0;
    @Override
    public int getLayoutId() {
        return R.layout.activity_lottery;
    }

    @Override
    public void initView() {
        mList = findViewById(R.id.list);
        mTitle = findViewById(R.id.title);
    }

    @Override
    public void initData() {
        super.initData();
        showLoadingDialog("");
        type = getIntent().getIntExtra("type",-1);
        adapter = new LotterAdapter(LotteryActivity.this,bean);
        mTitle.setTitle(strs[type]+"历史开奖");
        mList.setAdapter(adapter);

        LotteryModle.getDltData(type,new HttpCallBack<LotteryBean>() {
            @Override
            public void success(LotteryBean lotteryBean) {
               adapter.setData(lotteryBean);
                hideLoadingDialog();
            }

            @Override
            public void fail(String errorStr) {
                hideLoadingDialog();
            }
        });
    }

    @Override
    public void initListener() {
        super.initListener();
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LotteryActivity.this,WebBackActivity.class);
                String url2="https://m.aicai.com/zst/ssq.do?vt=5&clientType=1";
                if (type==0){
                    url2= "https://m.aicai.com/zst/ssq.do?vt=5&clientType=1";
                }else if (type==1){
                    url2= "https://m.aicai.com/zst/dlt.do?vt=5&clientType=1";
                }else if (type==2){
                    url2= "https://m.aicai.com/zst/fc3d.do?vt=5&clientType=1";
                }else if (type==3){
                    url2= "https://m.aicai.com/zst/pl3.do?vt=5&clientType=1";
                }else if (type==4){
                    url2= "https://m.aicai.com/zst/pl5.do?vt=5&clientType=1";
                }else if (type==5){
                    url2= "https://m.aicai.com/zst/ssq.do?vt=5&clientType=1";
                    return;
                }
                intent.putExtra("url",url2);
                startActivity(intent);
            }
        });
    }
}
