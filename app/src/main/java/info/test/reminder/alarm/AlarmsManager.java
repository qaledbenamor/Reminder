package info.test.reminder.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.GregorianCalendar;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by k.benamor on 26-09-2017.
 */

public class AlarmsManager {

    public static void scheduleNextAlarm(Context context)
    {

        Long time = Calendar.getInstance().getTimeInMillis() + 10000;
        Intent intentAlarm = new Intent(context, AlarmReciever.class);

        // create the object
        AlarmManager alarmsManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        //set the alarm for particular time
        alarmsManager.set(AlarmManager.RTC_WAKEUP,time, PendingIntent.getBroadcast(context,1,  intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));

    }


    // test only (unit test)
    public static int getTestValue(int value){
        return value * 3;
    }

}