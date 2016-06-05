package ske.joinpa.joinpa.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ske.joinpa.joinpa.models.Event;

/**
 * Created by TAWEESOFT on 5/30/16 AD.
 */
public class EventManager {

    private List<Event> eventList;
    private List<Event> tempEventList;
    private Gson gson = new Gson();

    public EventManager(List<Event> eventList) {
        this.eventList = eventList;
        tempEventList = new ArrayList<>();
    }

    public void addEvent(Event event) {
        eventList.add(event);
    }

    public void addEvent(List<Event> events) {
        clear();
        eventList.addAll(events);
    }

    public void clear() {
        eventList.clear();
    }

    public void clearTempList() {
        tempEventList.clear();
    }


    public List<Event> getEventList() {
        return eventList;
    }

    public void loadInternalEvent(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SP_KEY, context.MODE_PRIVATE);
        String eventsJson = sharedPreferences.getString(Constants.SP_EVENT_KEY , "[]");
        Type type = new TypeToken<List<Event>>(){}.getType();
        addEvent((List<Event>)gson.fromJson(eventsJson,type));
    }

    public void saveInternalEvent(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(
                Constants.SP_KEY, context.MODE_PRIVATE).edit();

        String eventsJson = gson.toJson(getEventList());
        editor.putString(Constants.SP_EVENT_KEY, eventsJson);
        editor.apply();
    }

    public List<Event> getTempEventList() {
        return tempEventList;
    }

    public void setTempEventList(List<Event> tempEventList) {
        this.tempEventList.clear();
        this.tempEventList.addAll(tempEventList);
    }

}
