package com.bjkj.library.okhttp;

import android.os.Handler;
import android.os.Looper;

import com.bjkj.library.utils.LogUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by liyou on 2018/3/28.
 */

public class OkHttpUtils {
    public static final MediaType TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpUtils okHttpUtils;
    private static OkHttpClient okHttpClient; // OKHttp网络请求
    public static Handler mHandler;
    /**
     * 单例模式  获取NetRequest实例
     *
     * @return netRequest
     */
    public OkHttpUtils(){

        OkHttpClient.Builder builder=new OkHttpClient().newBuilder();
        builder.connectTimeout(5*60, TimeUnit.SECONDS);
        builder.readTimeout(5*60,TimeUnit.SECONDS);
        builder.writeTimeout(5*60,TimeUnit.SECONDS);
        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtils.d("message = [" + message + "]");
            }
        });
        loggingInterceptor.setLevel(level);
        builder.addInterceptor(loggingInterceptor);
        okHttpClient = builder.build();
        mHandler = new Handler(Looper.getMainLooper());
    }
    private static OkHttpUtils getInstance() {

        if (okHttpUtils == null) {
            okHttpUtils = new OkHttpUtils();
        }
        return okHttpUtils;
    }

    public static void getRequestMethod(String url,final HttpCallBack<String> callBack){

        Request request = new Request.Builder().url(url).build();
        addCallBack(callBack,request);

    }

    public static void postJson(String url, String requestBodyJson, final HttpCallBack<String> callBack) {
        RequestBody body = RequestBody.create(TYPE_JSON, requestBodyJson);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        addCallBack(callBack, request);
    }

    public static void postForm(String url, Map<String,String> params, final HttpCallBack<String> callBack) {
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        Set<String> keySet = params.keySet();
        for(String key:keySet) {
            String value = params.get(key);
            formBodyBuilder.add(key,value);
        }
        FormBody formBody = formBodyBuilder.build();
        Request request = new Request
                .Builder()
                .post(formBody)
                .url(url)
                .build();
        addCallBack(callBack, request);
    }
    public static void addCallBack(final  HttpCallBack<String> callBack, Request request) {
        isNull();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                e.printStackTrace();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.fail(e.getMessage());
                    }
                });

            }

            @Override
            public void onResponse(final Call call, final Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String json = response.body().string();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.success(json);
                        }
                    });
                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.fail(response.message() );
                        }
                    });
                }
            }
        });
    }
    /**
     * 打印请求信息
     *
     * @param message
     */
    public void showMessage(String message) {
        LogUtils.d("OkHttpUtils"+" "+message);
    }
    public static void isNull(){
        if (okHttpClient==null){
            getInstance();
        }
    }
}
