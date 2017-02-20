package com.sk.tvschedule;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sk.tvschedule.Service.RestrofitService;

public class TVScheduler extends BroadcastReceiver {
    public TVScheduler() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent serviceIntent= new Intent(context, RestrofitService.class);
        context.startService(serviceIntent);
    }
}
