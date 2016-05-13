package io.joinpa.joinpa.managers;

import android.util.Log;

import java.util.Map;
import java.util.Observable;

import io.joinpa.joinpa.models.Token;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TAWEESOFT on 5/13/16 AD.
 */
public class LoadService extends Observable {

    private LoadService () {}

    public static LoadService newInstance() {
        return new LoadService();
    }

    public void signIn(Map<String, String> data) {
        RequestBody requestBody = HttpManager.getInstance().createRequestBody(data);
        APIService apiService = HttpManager.getInstance().getAPIService(APIService.class);
        Call<Token> call = apiService.signin(requestBody);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                setChanged();
                notifyObservers(response);
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Log.e("connection error" , t.getMessage());
                //todo handle errors
            }
        });
    }
}
