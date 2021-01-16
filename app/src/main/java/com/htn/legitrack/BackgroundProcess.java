package com.htn.legitrack;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;

import androidx.annotation.RequiresApi;


@RequiresApi(api = Build.VERSION_CODES.N)
public class BackgroundProcess {

    private static AlarmManager alarmMgr;
    private static PendingIntent alarmIntent;

    public static void execute() {
        // Set the alarm to start at approximately 12:00 a.m.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 23);

    // With setInexactRepeating(), you have to use one of the AlarmManager interval
    // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
    }

}
