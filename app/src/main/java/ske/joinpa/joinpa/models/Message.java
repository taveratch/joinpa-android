package ske.joinpa.joinpa.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TAWEESOFT on 5/20/16 AD.
 */
public class Message {

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
