package info.test.reminder.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import info.test.reminder.app.AppConfig;

/**
 * Created by k.benamor on 25-09-2017.
 */

public class NotificationHelper {

    public static NotificationCompat.Builder createNotificationBuider(Context context, String title,
                                                                      String message, int smallIcon) {
        return createNotificationBuider(context, title, message, smallIcon, null, 0, true, null);
    }

    public static NotificationCompat.Builder createNotificationBuider(Context context, String title,
                                                                      String message, int smallIcon, PendingIntent contentIntent) {
        return createNotificationBuider(context, title, message, smallIcon, null, 0, true, contentIntent);
    }

    public static NotificationCompat.Builder createNotificationBuider(Context context, String title, String message, int smallIcon, Bitmap largeIcon, int colorID,
                                                                      boolean isAutoCancel, PendingIntent contentIntent) {
        try {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setAutoCancel(isAutoCancel);
            builder.setGroup(AppConfig.GROUP_KEY);

            if (!TextUtils.isEmpty(title))
                builder.setContentTitle(title);
            if (!TextUtils.isEmpty(message))
                builder.setContentText(message);
            if (smallIcon != 0)
                builder.setSmallIcon(smallIcon);
            if (largeIcon != null)
                builder.setLargeIcon(largeIcon);
            if (colorID != 0)
                builder.setColor(ContextCompat.getColor(context, colorID));
            if (contentIntent != null)
                builder.setContentIntent(contentIntent);

            return builder;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void showNotification(Context context, int notiID, Notification notification) {
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notiID, notification);
    }

}
