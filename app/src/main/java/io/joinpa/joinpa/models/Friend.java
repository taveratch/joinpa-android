package io.joinpa.joinpa.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TAWEESOFT on 5/13/16 AD.
 */
public class Friend extends User {

    @SerializedName("isFriend")
    private boolean isFriend;

    public boolean isFriend() {
        return isFriend;
    }

    public void setFriend(boolean friend) {
        isFriend = friend;
    }
}
