package com.gjg.mobilesafe.receive;

import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import com.gjg.mobilesafe.R;
import com.gjg.mobilesafe.engine.GPSInfoProvider;

import java.util.Objects;

/**
 * 作者： ${高俊光}
 * 时间： 2016/4/16   22：54.
 */
public class SMSReceiver extends BroadcastReceiver {
    private static final String TAG = "SMSReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Objects[] pduses= (Objects[]) intent.getExtras().get("pdus");
        for(Object pdus : pduses){
            SmsMessage smsMessage=SmsMessage.createFromPdu((byte[])pdus);
            String content=smsMessage.getMessageBody();
            Log.i(TAG,"内容"+content);
            String sender=smsMessage.getOriginatingAddress();
            if("#*locationn*#".equals(content)){
                abortBroadcast();
                GPSInfoProvider provider= GPSInfoProvider.getInstance(context);
                String location=provider.getLocation();
                SmsManager manager=SmsManager.getDefault();
                if(!"".equals(location)){
                    manager.sendTextMessage(sender,null,location,null,null);
                }

            }else if("#*locknow*#".equals(content)){
                DevicePolicyManager devicePolicyManager= (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
                devicePolicyManager.resetPassword("123",0);
                devicePolicyManager.lockNow();
                abortBroadcast();

            }else if("#*wipedata*#".equals(content)){
                DevicePolicyManager devicePolicyManager= (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
                devicePolicyManager.wipeData(0);
                abortBroadcast();

            }else if("#*alarm*#".equals(content)){
                MediaPlayer mediaPlayer=MediaPlayer.create(context, R.raw.ylzs);
                mediaPlayer.setVolume(1.0f,1.0f);
                mediaPlayer.start();
                abortBroadcast();

            }

        }

    }
}
