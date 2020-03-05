package com.zdominguez.sdksandbox.firebase

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.Builder
import androidx.core.app.NotificationCompat.InboxStyle
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.RemoteMessage.Notification
import com.zdominguez.sdksandbox.MainActivity
import com.zdominguez.sdksandbox.R.drawable
import timber.log.Timber

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Timber.i("New Firebase messaging token received $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val notification = remoteMessage.notification
        sendNotification(notification)
    }

    private fun sendNotification(notification: Notification?) {
        val builder =
            Builder(this, CHANNEL_ID)
        val title = notification!!.title
        builder.setSmallIcon(drawable.ic_notification_green)
            .setContentTitle(title)
            .setContentText(notification.body)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setGroupSummary(true)
            .setAutoCancel(true)
            .setCategory(NotificationCompat.CATEGORY_PROMO)
        val style =
            InboxStyle()
        style.setBigContentTitle(title)
        builder.setStyle(style)
        val notificationIntent = Intent(this, MainActivity::class.java)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
        builder.setContentIntent(pendingIntent)
        val nm =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.cancel(NOTIFICATION_TAG,
            NOTIFICATION_REQUEST_CODE)
        nm.notify(NOTIFICATION_TAG,
            NOTIFICATION_REQUEST_CODE, builder.build())
    }

    companion object {
        const val NOTIFICATION_TAG = "notification_tag_fcm"
        const val NOTIFICATION_REQUEST_CODE = 101

        const val CHANNEL_ID = "channel_id"
    }
}