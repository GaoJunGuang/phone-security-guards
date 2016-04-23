package com.gjg.mobilesafe.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.gjg.mobilesafe.ui.LostProtectActivity;

/**
 * 作者： ${高俊光}
 * 时间： 2015/11/16   19：06.
 */
public class CallPhoneReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String number=getResultData();
        if("5201314".equals(number)){
            Intent lostIntent=new Intent(context, LostProtectActivity.class);
            //指定要激活的Intent在自己的任务栈
            lostIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(lostIntent);
            //不能通过abortBroadcast();终止广播
            setResultData(null);


        }

    }
}
