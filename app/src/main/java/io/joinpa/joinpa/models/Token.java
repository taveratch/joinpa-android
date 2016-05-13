package io.joinpa.joinpa.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TAWEESOFT on 5/13/16 AD.
 */
public class Token {
    @SerializedName("key")
    private String key;

    public Token(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
