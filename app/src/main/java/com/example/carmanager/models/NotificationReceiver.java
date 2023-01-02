package com.example.carmanager.models;

import android.accessibilityservice.AccessibilityService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.carmanager.DbManager;
import com.example.carmanager.MainActivity;
import com.example.carmanager.Notifications;
import com.example.carmanager.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DbManager dbManager = DbManager.instanceOfDatabase(context);
        dbManager.fillNotificationArrayList();
        for (Notification notification: Notification.listOfNotification){
            if(notification.getNotificationType()==1){
                try {
                    Date currentTime = Calendar.getInstance().getTime();
                    Date date = format.parse(notification.getDate());
                    if (currentTime.after(date) || currentTime.equals(date)){
                        createNotification(notification.getName(), notification.getDescription(), context);
                        dbManager.deleteNotificationInDb(notification);
                    }
                } catch (ParseException e) {
                }
            }
            else if(notification.getNotificationType()==0){
                dbManager.fillMileageArrayList();
                if (notification.getKilometre()<=Mileage.listOfMIleage.get(Mileage.listOfMIleage.size() - 1).getMileageValue()) {
                    createNotification(notification.getName(), notification.getDescription(), context);
                    dbManager.deleteNotificationInDb(notification);
                }
            }
        }
    }

    private void createNotification(String title,String content,Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "CHANNEL_ID")
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Przypomienie o "+title)
                .setContentText(content)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(new Random().nextInt() , builder.build());

    }
}
