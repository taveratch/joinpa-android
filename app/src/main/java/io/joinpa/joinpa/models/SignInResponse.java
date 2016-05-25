package io.joinpa.joinpa.models;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import io.joinpa.joinpa.managers.App;
import io.joinpa.joinpa.managers.LoadService;
import retrofit2.Response;

/**
 * Created by TAWEESOFT on 5/14/16 AD.
 */
public class SignInResponse extends ObjectResponse {

    private Map<String,String> data;
    private Context context;
    private Observer observer;

    public SignInResponse(Map<String , String> data , Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public void execute() {
        LoadService loadService = LoadService.newInstance();
        loadService.signIn(data,this);
    }

    @Override
    public void update(Observable observable, Object o) {
        Response<Token> response = (Response<Token>)o;
        Log.e("signin" , response.isSuccessful()+"");

        if (response.isSuccessful()) {
            Token token = response.body();
            app.saveToken(token , context);
            Log.e("token from signin" , token.getKey());
            VerifyResponse verifyResponse = new VerifyResponse(app.getToken().getKey());
            verifyResponse.addObserver(observer);
            verifyResponse.execute();
        } else {
            try {
                String errorMessage = response.errorBody().string();
                Log.e("error message", errorMessage);
                setSuccess(false);
                setMessage(errorMessage);
                setChanged();
                notifyObservers(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // TODO: 5/14/16 AD handle error

        }
    }

    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer);
        this.observer = observer;
    }
}
