package ske.joinpa.joinpa.models;

/**
 * Created by TAWEESOFT on 6/1/16 AD.
 */
public class NotificationReceiver {

    private static NotificationReceiver notificationReceiver;
    private Event event;
    private Friend friend;

    private NotificationReceiver() {

    }

    public static NotificationReceiver getInstance() {
        if (notificationReceiver == null)
            notificationReceiver = new NotificationReceiver();
        return notificationReceiver;
    }
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    public boolean isFriendNotified(){
        return friend != null;
    }

    public boolean isEventNotified() {
        return event != null;
    }
}
