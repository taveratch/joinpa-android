package ske.joinpa.joinpa.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Peter on 5/30/2016 AD.
 */
public class EventElement {

    @SerializedName("result")
    private List<Event> eventList;

    public List<Event> getEventList() {
        return eventList;
    }
}
