package com.zipper.zipcloset;

import android.content.Context;

import com.google.android.gcm.GCMBroadcastReceiver;
public class GCMReciever extends GCMBroadcastReceiver {
    @Override
    public String getGCMIntentServiceClassName(Context context){
        return "com.zipper.zipcloset.MyGCMLoggingReceiver";
    }
}