package info.test.reminder.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import info.test.reminder.activity.AlertActivity;
import info.test.reminder.notification.NotificationsManager;

/**
 * Created by k.benamor on 26-09-2017.
 */

public class AlarmReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationsManager.showStandardNotification(context);
        //AlarmsManager.scheduleNextAlarm(context);
        Intent intent1 = new Intent(context, AlertActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }
}
