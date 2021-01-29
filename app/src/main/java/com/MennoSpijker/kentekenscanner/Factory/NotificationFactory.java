package com.MennoSpijker.kentekenscanner.Factory;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.core.app.NotificationCompat;

import com.MennoSpijker.kentekenscanner.R;

public class NotificationFactory {
    Context context;

    public NotificationFactory(Context ctx) {
        context = ctx;
    }

    public void scheduleNotification(Notification notification, int DateOfExecution) {

        Intent notificationIntent = new Intent(context, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //long futureInMillis = SystemClock.elapsedRealtime() + delayInMillisecond;
        long futureInMillis = DateOfExecution;

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);

        System.out.println(alarmManager.getNextAlarmClock());
    }

    public Notification getNotification(String content) {
        //Notification.Builder builder = new Notification.Builder(context, NotificationPublisher.CHANNEL_ID);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NotificationPublisher.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_icon)
                .setContentTitle("Scheduled Notification")
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Notification notification = builder.build();
        return notification;
    }
}
