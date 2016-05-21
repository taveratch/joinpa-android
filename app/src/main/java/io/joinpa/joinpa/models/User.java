package io.joinpa.joinpa.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TAWEESOFT on 5/13/16 AD.
 */
public class User {
    @SerializedName("_id")
    private String id;

    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    @SerializedName("avatar")
    private int avatar;

    @SerializedName("friends")
    private List<Friend> friendList;

    @SerializedName("friendRequests")
    private List<Friend> friendRequest;

    @SerializedName("password")
    private String password;

    @SerializedName("confirmPassword")
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public List<Friend> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<Friend> friendList) {
        this.friendList = friendList;
    }

    public List<Friend> getFriendRequest() {
        return friendRequest;
    }

    public void setFriendRequest(List<Friend> friendRequest) {
        this.friendRequest = friendRequest;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
