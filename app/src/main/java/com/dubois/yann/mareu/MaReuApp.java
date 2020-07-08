package com.dubois.yann.mareu;

import android.app.Application;
import android.content.Context;

import net.danlew.android.joda.JodaTimeAndroid;

public class MaReuApp extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
        MaReuApp.mContext = getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }
}
