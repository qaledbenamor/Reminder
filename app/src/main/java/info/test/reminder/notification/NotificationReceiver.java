package info.test.reminder.notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.RemoteInput;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import info.test.reminder.app.AppConfig;

/**
 * Created by k.benamor on 25-09-2017.
 */

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        int noti_id=intent.getIntExtra("notiID",0);

        if(action.equals(AppConfig.POSITIVE_CLICK)) {
            Log.d("Action Positive","Pressed");
            cancel(context,noti_id);
        } else if(action.equals(AppConfig.NEGATIVE_CLICK)) {
            Log.d("Action Negative","Pressed");
            cancel(context,noti_id);
        }
        else if(action.equals(AppConfig.REPLY_CLICK)) {
            Log.d("Action Reply","Pressed"+getMessageText(intent));
            cancel(context,noti_id);
        }
    }

    public static void cancel(Context context, int id){
        NotificationManager notificacaoManager = (NotificationManager) context.getSystemService(Activity.NOTIFICATION_SERVICE);
        notificacaoManager.cancel(id);
    }

    private CharSequence getMessageText(Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
            if (remoteInput != null) {
                return remoteInput.getCharSequence(AppConfig.REPLY_TEXT_KEY);
            }
        }
        return null;
    }
}
