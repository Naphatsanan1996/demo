package com.example.android.tflitecamerademo.settime;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.example.android.tflitecamerademo.R;
import com.example.android.tflitecamerademo.activity.Main2Activity;


public class NotificationHelper extends ContextWrapper {
    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";
    private NotificationManager mManager;


    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

     public NotificationCompat.Builder getChannelNotification() {
         Intent nIntent  = new Intent(this, Main2Activity.class) ;
         PendingIntent nPendingIntent = PendingIntent.getActivity(this,1,nIntent,
                 PendingIntent.FLAG_UPDATE_CURRENT);
         MediaPlayer ring= MediaPlayer.create(NotificationHelper.this,R.raw.oringz);
         ring.start();

        return new  NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("สวัสดีครับ")
                .setContentText("มาออกกำลังกายกันเถอะครับ")
                .setContentIntent(nPendingIntent)
                .setAutoCancel(false)
                .setSmallIcon(R.drawable.stretching);
    }


}
