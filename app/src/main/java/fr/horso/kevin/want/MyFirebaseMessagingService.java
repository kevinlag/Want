package fr.horso.kevin.want;

import android.app.NotificationChannel;
import android.app.NotificationManager;

import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    private static final String CANAL =  "MyNotifCanal" ;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);


        String myMessage = remoteMessage.getNotification().getBody();
        Log.d("FirebaseMessage", "vous venez de recevoir une notification : " + myMessage);

        /// action de redirection ver application







        ///cree une notification

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this , CANAL);
        notificationBuilder.setContentTitle("Ma super notif");
        notificationBuilder.setContentText(myMessage);

        /// une icone qui represente l'application


        /// cree la vibration
        long [] vibrationPatterne = {500, 1000};
        notificationBuilder.setVibrate(vibrationPatterne);


        //// envoyer la notification

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        ///pour les version superrieur ou =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = getString(R.string.notification_channel_id);
            String channelTitle = getString(R.string.notification_channel_title);
            String channelDescription = getString(R.string.notification_channel_desc);
            NotificationChannel channel = new NotificationChannel(channelId,channelTitle,NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(channelDescription);
            notificationManager.createNotificationChannel(channel);
            notificationBuilder.setChannelId(channelId);
        }
        ////////
        notificationManager.notify(1,notificationBuilder.build());
    }
}
