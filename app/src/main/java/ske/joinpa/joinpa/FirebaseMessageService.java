package ske.joinpa.joinpa;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import ske.joinpa.joinpa.managers.Constants;
import ske.joinpa.joinpa.models.NotificationHandler;
import ske.joinpa.joinpa.models.NotificationReceiver;
import ske.joinpa.joinpa.ui.views.SplashScreenActivity;

/**
 * Created by TAWEESOFT on 5/28/16 AD.
 */
public class FirebaseMessageService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: 5/28/16 AD Handle message and show notification
        String body  = remoteMessage.getData().get("body");
        String title = remoteMessage.getData().get("title");
        sendNotification(title, body);
    }


    private void sendNotification(String title, String messageBody) {
        System.out.println("Notification Message " + messageBody);
        Gson gson = new Gson();
        NotificationHandler handler = gson.fromJson(messageBody,NotificationHandler.class);
        NotificationReceiver receiver = NotificationReceiver.getInstance();
        if (handler.getStatus() == Constants.NOTIFICATION_EVENT){
            receiver.setEvent(handler.getEvent());
            System.out.println(handler.getEvent().getName());
        }else if(handler.getStatus() == Constants.NOTIFICATION_FRIEND_REQUEST){
            receiver.setFriend(handler.getFriend());
        }
        Intent intent = new Intent(this, SplashScreenActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logo3)
                .setContentTitle(title)
                .setContentText(handler.getMessage())
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }

}
