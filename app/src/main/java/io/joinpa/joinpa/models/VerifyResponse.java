package io.joinpa.joinpa.models;

import android.util.Log;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import io.joinpa.joinpa.managers.LoadService;
import retrofit2.Response;

/**
 * Created by TAWEESOFT on 5/14/16 AD.
 */
public class VerifyResponse extends ObjectResponse {

    private String token;
    public VerifyResponse(String token) {
        this.token = token;
    }

    @Override
    public void execute() {
        Log.e("xxx", "123");
        LoadService loadService = LoadService.newInstance();
        loadService.verify(token,this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if( o == null ) return;
        Response<User> response = (Response<User>)o;
        if(response.isSuccessful()) {
            setSuccess(true);
            setMessage("Success");
            setData(response);
        }else{
            try {
                setSuccess(false);
                setMessage(response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        setChanged();
        notifyObservers(this);
    }
}
