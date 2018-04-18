package com.bjke.project1.modle;

import com.bjke.project1.bean.QuickBean;
import com.bjke.project1.bean.QuickLotteryBean;
import com.bjke.project1.bean.SSCBean;
import com.bjkj.library.okhttp.HttpCallBack;
import com.bjkj.library.okhttp.OkHttpUtils;
import com.bjkj.library.utils.SPUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liyou on 2018/4/11.
 */

public class SSCModle {

    public static void getSSCName(final HttpCallBack<SSCBean> callBack){
        String url = "http://m.cpbang.com/index.php/PhoneApi/request/ac/userWinList ";
        Map<String,String> map=new HashMap<>();
        map.put("app_version","1.5.6");
        map.put("net_type","Wi-Fi");
        map.put("client_type","2");
        OkHttpUtils.postForm(url, map, new HttpCallBack<String>() {
            @Override
            public void success(String s) {
                Gson gson =new Gson();
                SSCBean bean= gson.fromJson(s,SSCBean.class);
                callBack.success(bean);
            }

            @Override
            public void fail(String errorStr) {
                callBack.fail(errorStr);
            }
        });
    }


    public static void getQuickName(final HttpCallBack<QuickBean> callBack){
        String url = "http://app1.gdemcg.com:9000/index.php/PhoneApi/request/ac/getCPLogInfo";
        Map<String,String> map=new HashMap<>();
        map.put("app_version","1.5.6");
        map.put("net_type","Wi-Fi");
        map.put("client_type","2");
        map.put("pcount","1");
        OkHttpUtils.postForm(url, map, new HttpCallBack<String>() {
            @Override
            public void success(String s) {
                Gson gson =new Gson();
                QuickBean bean= gson.fromJson(s,QuickBean.class);
                SPUtils.put("getQuickName",s);
                callBack.success(bean);
            }

            @Override
            public void fail(String errorStr) {
                callBack.fail(errorStr);
            }
        });
    }

    public static void getQuickLottery(String tag,final HttpCallBack<QuickLotteryBean> callBack){
        String url = "http://m.cpbang.com/index.php/PhoneApi/request/ac/getKjCplog";

//        pageid=0&app_version=1.5.6&client_type=2&tag=jsk3&pcount=20&net_type=Wi-Fi
        Map<String,String> map=new HashMap<>();
        map.put("app_version","1.5.6");
        map.put("net_type","Wi-Fi");
        map.put("client_type","2");
        map.put("pcount","20");
        map.put("tag",tag);
        map.put("pageid","0");
        OkHttpUtils.postForm(url, map, new HttpCallBack<String>() {
            @Override
            public void success(String s) {
                Gson gson =new Gson();
                QuickLotteryBean bean= gson.fromJson(s,QuickLotteryBean.class);
                SPUtils.put("getQuickLottery",s);
                callBack.success(bean);
            }

            @Override
            public void fail(String errorStr) {
                callBack.fail(errorStr);
            }
        });
    }
}
