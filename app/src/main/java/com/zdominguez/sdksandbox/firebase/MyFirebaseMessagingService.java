package com.zdominguez.sdksandbox.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.zdominguez.sdksandbox.MainActivity;
import com.zdominguez.sdksandbox.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static final String NOTIFICATION_TAG = "notification_tag_fcm";
    public static final int NOTIFICATION_REQUEST_CODE = 101;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        sendNotification(notification);
    }

    private void sendNotification(RemoteMessage.Notification notification) {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        String title = notification.getTitle();
        builder.setSmallIcon(R.drawable.ic_notification_green)
                .setContentTitle(title)
                .setContentText(notification.getBody())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setGroupSummary(true)
                .setAutoCancel(true)
                .setCategory(NotificationCompat.CATEGORY_PROMO);
        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        style.setBigContentTitle(title);
        builder.setStyle(style);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        builder.setContentIntent(pendingIntent);

        final NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.cancel(NOTIFICATION_TAG, NOTIFICATION_REQUEST_CODE);
        nm.notify(NOTIFICATION_TAG, NOTIFICATION_REQUEST_CODE, builder.build());
    }
}
