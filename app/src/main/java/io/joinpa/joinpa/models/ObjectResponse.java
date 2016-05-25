package io.joinpa.joinpa.models;

import java.util.Observable;
import java.util.Observer;

import io.joinpa.joinpa.managers.App;

/**
 * Created by TAWEESOFT on 5/14/16 AD.
 */
public abstract class ObjectResponse extends Observable implements Observer {

    private boolean isSuccess;
    private String message;
    private Object data;
    App app;

    public ObjectResponse() {
        app = App.getInstance();
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public abstract void execute();
}
