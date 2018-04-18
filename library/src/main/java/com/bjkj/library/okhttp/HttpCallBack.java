package com.bjkj.library.okhttp;

/**
 * Created by liyou on 2018/3/28.
 */

public interface HttpCallBack<T> {
    void success(T t);
    void fail(String errorStr);
}
