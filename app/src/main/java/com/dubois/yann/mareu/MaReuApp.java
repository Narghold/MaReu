package com.dubois.yann.mareu;

import android.annotation.SuppressLint;
import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

public class MaReuApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
    }
}
