package ske.joinpa.joinpa.managers.commands;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import ske.joinpa.joinpa.managers.App;
import ske.joinpa.joinpa.managers.LoadService;
import ske.joinpa.joinpa.models.User;
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
        App app = App.getInstance();
        LoadService loadService = LoadService.newInstance();
        Map<String , String> map = new HashMap<>();
        map.put("token", token);
        map.put("deviceKey", FirebaseInstanceId.getInstance().getToken());
        Log.e("deviceKey", FirebaseInstanceId.getInstance().getToken());
        loadService.verify(map, this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if ( o == null ) return;
        Response<User> response = (Response<User>)o;
        if (response.isSuccessful()) {
            setSuccess(true);
            setMessage("Success");
            Log.e("rrrr", response.body().getUsername());
            setData(response);
        } else {
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
