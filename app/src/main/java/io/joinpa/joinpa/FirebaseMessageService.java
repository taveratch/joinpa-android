package io.joinpa.joinpa;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import io.joinpa.joinpa.managers.Constants;
import io.joinpa.joinpa.models.NotificationHandler;
import io.joinpa.joinpa.models.NotificationReceiver;
import io.joinpa.joinpa.ui.views.SignInActivity;
import io.joinpa.joinpa.ui.views.SplashScreenActivity;

/**
 * Created by TAWEESOFT on 5/28/16 AD.
 */
public class FirebaseMessageService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: 5/28/16 AD Handle message and show notification
        Log.e("Received Message Noti" , remoteMessage.getNotification().getBody());
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.logo);
        builder.setContentTitle(remoteMessage.getNotification().getTitle()+"1");
        Gson gson = new Gson();
        NotificationHandler handler = gson.fromJson(remoteMessage.getNotification().getBody(),NotificationHandler.class);
        NotificationReceiver receiver = NotificationReceiver.getInstance();
        System.out.println("message " + handler.getMessage());
        builder.setContentText(handler.getMessage());
        Intent intent = new Intent(this , SplashScreenActivity.class);
        if (handler.getStatus() == Constants.NOTIFICATION_EVENT){
            receiver.setEvent(handler.getEvent());
            System.out.println(handler.getEvent().getName());
        }else{
            receiver.setFriend(handler.getFriend());
        }
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(12345, builder.build());
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//// Adds the back stack for the Intent (but not the Intent itself)
//        stackBuilder.addParentStack(SplashScreenActivity.class);
//// Adds the Intent that starts the Activity to the top of the stack
//        stackBuilder.addNextIntent(intent);
//        PendingIntent resultPendingIntent =
//                stackBuilder.getPendingIntent(
//                        0,
//                        PendingIntent.FLAG_UPDATE_CURRENT
//                );
//        builder.setContentIntent(resultPendingIntent);
//        NotificationManager mNotificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//// mId allows you to update the notification later on.
//        mNotificationManager.notify(12345, builder.build());
    }


}
