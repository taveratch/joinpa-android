package io.joinpa.joinpa.managers.Commands;

import android.util.Log;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import io.joinpa.joinpa.managers.LoadService;
import io.joinpa.joinpa.models.User;
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
        LoadService loadService = LoadService.newInstance();
        Map<String , String> map = new HashMap<>();
        map.put("token" , token);
        // TODO: 5/20/16 AD get device key from device 
        map.put("deviceKey" , "KeyFromSteve");
        loadService.verify(map,this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if( o == null ) return;
        Response<User> response = (Response<User>)o;
        if(response.isSuccessful()) {
            setSuccess(true);
            setMessage("Success");
            Log.e("rrrr" , response.body().getUsername());
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
