package com.example.ashwin.broadcastreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class LocalBroadcastReceiver extends BroadcastReceiver {
    public static final String ACTION = "new_message";
    private Activity activity;

    public LocalBroadcastReceiver(Activity a) {
        //activity = a;  // memory leak
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.w(MainActivity.TAG, "LocalBroadcastReceiver | onReceive | intent.categories: " + intent.getCategories() + " | intent.action: " + intent.getAction() + " | thread: " + Thread.currentThread().getName());
        if (intent.getCategories() != null && intent.getCategories().contains("cat1")) {
            Log.w(MainActivity.TAG, "  LocalBroadcastReceiver | onReceive | cat1");
        }
    }
}
