package io.joinpa.joinpa.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TAWEESOFT on 6/1/16 AD.
 */
public class NotificationHandler {

    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private String message;

    @SerializedName("event")
    private Event event;

    @SerializedName("friend")
    private Friend friend;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
}
