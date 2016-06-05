package ske.joinpa.joinpa.managers.commands;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import ske.joinpa.joinpa.managers.App;
import ske.joinpa.joinpa.managers.LoadService;
import ske.joinpa.joinpa.models.Token;
import retrofit2.Response;


public class SignUpResponse extends ObjectResponse {

    private Map<String,String> data;
    private Context context;
    private Observer observer;
    private App app;

    public SignUpResponse(Map<String, String> data, Context context){
        this.data = data;
        this.context = context;
        app = App.getInstance();
    }

    @Override
    public void execute() {
        LoadService loadService = LoadService.newInstance();
        loadService.signUp(data, this);
    }

    @Override
    public void update(Observable observable, Object o) {
        Response<Token> response = (Response<Token>)o;
        if (response.isSuccessful()) {
            Token token = response.body();
            app.saveToken(token, context);
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
