package io.joinpa.joinpa.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by TAWEESOFT on 5/29/16 AD.
 */
public class Event implements Serializable{

    @SerializedName("_id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("host")
    private User host;

    @SerializedName("date")
    private Date date;

    @SerializedName("icon")
    private int icon;

    private boolean isPrivate;

    @SerializedName("place")
    private Place place;

    @SerializedName("joinedList")
    private List<Friend> joinedList;

    @SerializedName("pendingList")
    private List<Friend> pendingList;

    @SerializedName("declinedList")
    private List<Friend> declinedList;

    private long timeStamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;
        return getId().equals(event.getId());
    }
}
