package com.example.an.bloodgroupssearchingsystem.Notificaiton;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.example.an.bloodgroupssearchingsystem.Model.Blood.Events;
import com.example.an.bloodgroupssearchingsystem.R;
import com.example.an.bloodgroupssearchingsystem.View.Blood.BloodFragment;
import com.example.an.bloodgroupssearchingsystem.View.Login.MainActivity;
import com.example.an.bloodgroupssearchingsystem.View.Menu.MenuActivity;

import static android.support.v4.app.NotificationCompat.VISIBILITY_PUBLIC;

public class EvensNotificationHelper extends NotificationHelper {
    private static final int ACTION_PREV = 0;
    private static final int ACTION_PLAY = 1;
    private static final int ACTION_NEXT = 2;
    private NotificationCompat.Builder mBuilder;
    private Context context;
    private NotificationManager mNotificationManager;


    public EvensNotificationHelper(Context context) {
        super(context);
        this.context = context;
    }

    public void ActionNotification(String name,String content) {
        // Create an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(context, MenuActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent resultPedingIntent = PendingIntent.getActivity(context, 0, resultIntent, 0);
        mBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_airline_seat_flat_while_24dp)
                .setChannelId(NOTIFICATION_CHANNEL_ID)
                .setContentTitle(name)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setShowWhen(false)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.imgblood))
                .setStyle(new android.support.v4.media.app.NotificationCompat.MediaStyle())
                .setAutoCancel(true)
                .setColor(ContextCompat.getColor(context,R.color.backgroud_end))
                .setContentIntent(resultPedingIntent)
                .setVisibility(VISIBILITY_PUBLIC);
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}
