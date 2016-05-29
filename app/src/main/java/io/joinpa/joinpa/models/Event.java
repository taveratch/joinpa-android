package io.joinpa.joinpa.models;

import java.util.Date;
import java.util.List;

/**
 * Created by TAWEESOFT on 5/29/16 AD.
 */
public class Event {

    private String name;
    private User host;
    private Date date;
    private int icon;
    private boolean isPrivate;
    private Place place;
    private List<Friend> joinedList;
    private List<Friend> pendingList;
    private List<Friend> declinedList;
    private long timeStamp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public List<Friend> getJoinedList() {
        return joinedList;
    }

    public void setJoinedList(List<Friend> joinedList) {
        this.joinedList = joinedList;
    }

    public List<Friend> getPendingList() {
        return pendingList;
    }

    public void setPendingList(List<Friend> pendingList) {
        this.pendingList = pendingList;
    }

    public List<Friend> getDeclinedList() {
        return declinedList;
    }

    public void setDeclinedList(List<Friend> declinedList) {
        this.declinedList = declinedList;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
