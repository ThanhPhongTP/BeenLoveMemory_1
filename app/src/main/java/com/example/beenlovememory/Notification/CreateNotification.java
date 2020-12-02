package com.example.beenlovememory.Notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.beenlovememory.Main;
import com.example.beenlovememory.R;

public class CreateNotification extends BroadcastReceiver {

    public static Notification notification;

    public static void createNotification(Context context) {
        Intent intent = new Intent(context, Main.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

//            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification);
//        Intent intent1 = new Intent(context, CreateNotification.class);
//        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

            Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.avatar);
            Bitmap icon1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.heart);


            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.notification);

            notification = new NotificationCompat.Builder(context, "LOVE")
                    .setCustomBigContentView(remoteViews)
                    .setSmallIcon(R.drawable.ic_camera_alt_black_24dp)
                    .setContentTitle("Notification")
                    .setContentText("abc")
//                    .setLargeIcon(icon)
                    .setOnlyAlertOnce(true)
                    .setShowWhen(false)

                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(pendingIntent)
                    .build();
            notificationManagerCompat.notify(1, notification);
//            Log.d("hhhhj", String.valueOf(notification));
        }



    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        intent = new Intent(context, Main.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "CHANEL_ID")
//                .setSmallIcon(R.drawable.heart)
//                .setContentTitle("BLM")
//                .setContentText("ABC")
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                .setContentIntent(pendingIntent)
//                .setAutoCancel(true);
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
//        notificationManager.notify(0, mBuilder.build());

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.cancel(1);
    }
}
