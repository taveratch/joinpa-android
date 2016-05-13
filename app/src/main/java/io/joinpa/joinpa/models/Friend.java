package io.joinpa.joinpa.models;

/**
 * Created by TAWEESOFT on 5/13/16 AD.
 */
public class Friend extends User {
    private boolean isFriend;

    public boolean isFriend() {
        return isFriend;
    }

    public void setFriend(boolean friend) {
        isFriend = friend;
    }
}
