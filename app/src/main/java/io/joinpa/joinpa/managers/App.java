package io.joinpa.joinpa.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import io.joinpa.joinpa.models.Event;
import io.joinpa.joinpa.models.Friend;
import io.joinpa.joinpa.models.InternalData;
import io.joinpa.joinpa.models.Token;
import io.joinpa.joinpa.models.User;

/**
 * Created by TAWEESOFT on 5/14/16 AD.
 */
public class App {

    private static App app;
    private InternalData internalData;
    private Gson gson;
    private App() {
        internalData = InternalData.getInstance();
        gson = new Gson();
    }

    public static App getInstance() {
        if ( app == null ) app = new App();
        return app;
    }

    public void saveToken(Token token , Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constants.SP_KEY , context.MODE_PRIVATE).edit();
        editor.putString("token" , token.getKey());
        editor.apply();
        loadToken(context);
    }

    private void clearToken(Context context) {
        getInternalData().token = null;
        SharedPreferences.Editor editor = context.getSharedPreferences(Constants.SP_KEY , context.MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
    }

    public InternalData getInternalData() {
        return internalData;
    }

    public void loadToken(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SP_KEY , context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token" , null);
        internalData.token = new Token(token);
    }

    public Token getToken() {
        return getInternalData().token;
    }

    public void setUser(User user) {
        getInternalData().user = user;
    }

    public User getUser() {
        return getInternalData().user;
    }

    public void clear(Context context) {
        clearToken(context);
        setUser(null);
    }

    public void refreshFriendList(List<Friend> friends) {
        getUser().getFriendList().clear();
        getUser().getFriendList().addAll(friends);
    }

    public void loadInternalEvent(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SP_KEY , context.MODE_PRIVATE);
        String eventsJson = sharedPreferences.getString(Constants.SP_EVENT_KEY , "[]");
        Type type = new TypeToken<List<Event>>(){}.getType();
        getInternalData().events = gson.fromJson(eventsJson,type);
    }

    public void saveInternalEvent(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constants.SP_KEY , context.MODE_PRIVATE).edit();
        String eventsJson = gson.toJson(getInternalData().events);
        editor.putString(Constants.SP_EVENT_KEY , eventsJson);
        editor.apply();
    }




}
