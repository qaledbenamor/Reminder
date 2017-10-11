package info.test.reminder.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.support.v4.content.ContextCompat;

import java.util.Random;
import info.test.reminder.R;
import info.test.reminder.app.AppConfig;

/**
 * Created by k.benamor on 25-09-2017.
 */

public class NotificationsManager {

    public static void showStandardNotification(Context context) {

        int notificationId = new Random().nextInt();

        Intent intent = new Intent(context, NotificationActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder notification = NotificationHelper.createNotificationBuider(context,
                "Notification", "Standard notification!", R.drawable.ic_notification, pIntent);

        NotificationHelper.showNotification(context, notificationId, notification.build());
    }

    public static void showHeadsUpNotification(Context context) {
        int notificationId = new Random().nextInt();

        Intent intent = new Intent(context, NotificationActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);

        Intent positive = new Intent(context, NotificationReceiver.class);
        positive.putExtra("notiID", notificationId);
        positive.setAction(AppConfig.POSITIVE_CLICK);

        PendingIntent pIntent_positive = PendingIntent.getBroadcast(context, notificationId, positive, PendingIntent.FLAG_CANCEL_CURRENT);

        Intent negative = new Intent(context, NotificationReceiver.class);
        negative.putExtra("notiID", notificationId);
        negative.setAction(AppConfig.NEGATIVE_CLICK);

        PendingIntent pIntent_negative = PendingIntent.getBroadcast(context, notificationId, negative, PendingIntent.FLAG_CANCEL_CURRENT);


        NotificationCompat.Builder notification = NotificationHelper.createNotificationBuider(context,
                "Notification", "Heads-Up Notification!", R.drawable.ic_notification, pIntent);

        notification.setPriority(Notification.PRIORITY_HIGH).setVibrate(new long[0]);

        notification.addAction(new NotificationCompat.Action(R.drawable.ic_notification, "Postive", pIntent_positive));
        notification.addAction(new NotificationCompat.Action(R.drawable.ic_notification, "Negative", pIntent_negative));

        NotificationHelper.showNotification(context, notificationId, notification.build());
    }

    public static void showRemoteInputNotification(Context context) {

        int notificationId = new Random().nextInt();

        NotificationCompat.Builder notification = NotificationHelper.createNotificationBuider(context,
                "Remote Input", "Notification with Remote Input", R.drawable.ic_notification);

        notification.setGroupSummary(true);
        notification.setGroup("KEY_NOTIFICATION_GROUP1");

        Intent remote_intent = new Intent(context, NotificationReceiver.class);
        remote_intent.putExtra("notiID", notificationId);
        remote_intent.setAction(AppConfig.REPLY_CLICK);

        PendingIntent pIntent_positive = PendingIntent.getBroadcast(context, notificationId, remote_intent, PendingIntent.FLAG_CANCEL_CURRENT);


        RemoteInput remoteInput = new RemoteInput.Builder(AppConfig.REPLY_TEXT_KEY)
                .setLabel("Type Something")
                .build();

        // Create the reply action and add the remote input.

        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_notification, "Reply", pIntent_positive)
                        .addRemoteInput(remoteInput)
                        .build();

        notification.addAction(action);
        notification.setColor(ContextCompat.getColor(context, R.color.colorAccent));

        NotificationHelper.showNotification(context, notificationId, notification.build());
    }

    public static void showSummaryNotification(Context context) {

        int notificationId = new Random().nextInt();

        Intent intent = new Intent(context, NotificationActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);

        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ic_notification);

        NotificationCompat.Builder summary_notification = NotificationHelper.createNotificationBuider(context,
                "2 new messages", "Bundle notification!", R.drawable.ic_notification, pIntent);

        summary_notification.setStyle(new NotificationCompat.InboxStyle()
                .addLine("Ashwin  Check this out")
                .addLine("Ranjith  Launch Party")
                .setBigContentTitle("2 new messages")
                .setSummaryText("2 new messages"))
                .setLargeIcon(largeIcon)
                .setGroupSummary(true)
                .setContentIntent(pIntent)
                .setGroup(AppConfig.GROUP_KEY).build();

        int notificationId1 = new Random().nextInt();

        NotificationCompat.Builder notification1 = NotificationHelper.createNotificationBuider(context,
                "Ashwin", "Check this out", R.drawable.ic_notification, pIntent);

        int notificationId2 = new Random().nextInt();

        NotificationCompat.Builder notification2 = NotificationHelper.createNotificationBuider(context,
                "Ranjith", "Launch Party", R.drawable.ic_notification, pIntent);

        NotificationHelper.showNotification(context, notificationId1, notification1.build());
        NotificationHelper.showNotification(context, notificationId2, notification2.build());

        NotificationHelper.showNotification(context, notificationId, summary_notification.build());
    }

}
