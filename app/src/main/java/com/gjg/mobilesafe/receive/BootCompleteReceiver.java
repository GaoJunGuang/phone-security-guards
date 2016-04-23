package com.gjg.mobilesafe.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

/**
 * 作者： ${高俊光}
 * 时间： 2016/4/10   16：31.
 */
public class BootCompleteReceiver extends BroadcastReceiver{
    private SharedPreferences sharedPreferences;


    @Override
    public void onReceive(Context context, Intent intent) {
        //判断手机是否处于保护状态
        sharedPreferences=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        boolean isprotected=sharedPreferences.getBoolean("isprotecting",false);
        if(isprotected){
            TelephonyManager telephonyManager= (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String currentsim=telephonyManager.getSimSerialNumber();
            String realsim=sharedPreferences.getString("sim","");
            if(!realsim.equals(currentsim)){
                SmsManager smsManager=SmsManager.getDefault();
                String destinationAddress=sharedPreferences.getString("safenumber","");
                smsManager.sendTextMessage(destinationAddress,null,"sim卡发生改变，手机可能被盗",null,null);

            }

        }

    }
}
