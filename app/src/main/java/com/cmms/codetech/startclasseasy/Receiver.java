package com.cmms.codetech.startclasseasy;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

/**
 * Created by daryl on 07/11/2015.
 */
public class Receiver extends BroadcastReceiver {
    private static Ringtone ringtone  = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        //this will update the UI with message
        AttendanceActivity inst = AttendanceActivity.instance();
        //inst.setAlarmText("Alarm! Wake up! Wake up!");
        Log.e("111", "111");

        //this will sound the alarm tone
        //this will sound the alarm once, if you wish to
        //raise alarm in loop continuously then use MediaPlayer and setLooping(true)
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();

        //this will send a notification message
        ComponentName comp = new ComponentName(context.getPackageName(),
                Service.class.getName());
    }

    public static void stopRingtone() {
        ringtone.stop();
    }
}
