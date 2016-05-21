package io.joinpa.joinpa.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TAWEESOFT on 5/21/16 AD.
 */
public class Element {

    @SerializedName("result")
    private List<Friend> searchList;

    @SerializedName("friends")
    private List<Friend> friendList;

    public List<Friend> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Friend> searchList) {
        this.searchList = searchList;
    }

    public List<Friend> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<Friend> friendList) {
        this.friendList = friendList;
    }
}
