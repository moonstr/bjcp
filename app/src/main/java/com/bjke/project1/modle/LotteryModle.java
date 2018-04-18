package com.bjke.project1.modle;

import com.bjke.project1.bean.HotBean;
import com.bjke.project1.bean.LotteryBean;
import com.bjkj.library.okhttp.HttpCallBack;
import com.bjkj.library.okhttp.OkHttpUtils;
import com.google.gson.Gson;


/**
 * Created by liyou on 2018/3/28.
 */

public class LotteryModle {
    public static void getDltData(int type ,final HttpCallBack<LotteryBean> callBack){
        String url ="http://f.apiplus.net/dlt-10.json";
        if (type==0){
            url =  "http://f.apiplus.net/ssq-10.json";
        }else if (type==1){
            url =  "http://f.apiplus.net/dlt-10.json";
        }else if (type==2){
            url = "http://f.apiplus.net/fc3d-10.json";
        }else if (type==3){
            url = "http://f.apiplus.net/pl3-10.json";
        }else if (type==4){
            url = "http://f.apiplus.net/pl5-10.json";
        }else if (type==5){
            url = "http://f.apiplus.net/qxc-10.json";
        }
        OkHttpUtils.getRequestMethod(url, new HttpCallBack<String>() {
            @Override
            public void success(String str) {
                Gson gson = new Gson();
                try {
                    LotteryBean bean =   gson.fromJson(str,LotteryBean.class);
                    callBack.success(bean);
                }catch (Exception ex){
                    callBack.fail("");
                }

            }

            @Override
            public void fail(String errorStr) {
                callBack.fail("");
            }
        });

    }

    public static void getHotData(String url,final HttpCallBack<HotBean> callBack){
        String url2 ="http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300203&src=0000100001%7C6000003060";

        OkHttpUtils.getRequestMethod(url, new HttpCallBack<String>() {
            @Override
            public void success(String str) {
                Gson gson = new Gson();
                HotBean bean =   gson.fromJson(str,HotBean.class);
                callBack.success(bean);
            }

            @Override
            public void fail(String errorStr) {
            }
        });
    }
}
