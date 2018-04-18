package com.bjke.project1.utils;

import android.os.Handler;
import android.os.Looper;

import java.lang.ref.WeakReference;

public class HandlerUtil<T> extends Handler {
    private final WeakReference<T> mActivity;

    public HandlerUtil(T activity) {
        super(Looper.getMainLooper());
        mActivity = new WeakReference<T>(activity);
    }

}