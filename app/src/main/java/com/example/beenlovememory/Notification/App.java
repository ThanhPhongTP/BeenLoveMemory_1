package com.example.beenlovememory.Notification;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.example.beenlovememory.Notification.CreateNotification;

public class App extends Application {

    public static final String ID = "BLM";

    @Override
    public void onCreate() {
        super.onCreate();
        CreateNotificationChnel();

    }

    private void CreateNotificationChnel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(ID, "Thông báo", NotificationManager.IMPORTANCE_HIGH);
//            channel.setDescription("dhajkd");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}
