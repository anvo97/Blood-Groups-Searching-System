package com.example.an.bloodgroupssearchingsystem.Notificaiton;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import com.example.an.bloodgroupssearchingsystem.R;

public class NotificationHelper {
    private static final String TAG = NotificationHelper.class.getName();
    protected NotificationManager mNotificationManager;
    protected NotificationChannel mNotificationChannel;
    protected static final String NOTIFICATION_CHANNEL_ID = "sun_notify_channel";
    protected static final int NOTIFICATION_ID = 100;

    public NotificationHelper(Context context) {
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // The user-visible name of the channel.
            CharSequence name = context.getString(R.string.channel_name);
            // The user-visible description of the channel.
            String description = context.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            //Create Notification channel object
            mNotificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance);
            //Configure the notification channel
            mNotificationChannel.setDescription(description);
            mNotificationChannel.enableLights(true);
            mNotificationChannel.setLightColor(Color.RED);
            mNotificationChannel.enableVibration(true);
            mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.createNotificationChannel(mNotificationChannel);
        }
    }

    public void notify(int notificationId, Notification notification) {
        try {
            mNotificationManager.notify(notificationId, notification);
        } catch (RuntimeException e) {
            Log.e(TAG, "Error post notification :", e);
        }
    }
}
