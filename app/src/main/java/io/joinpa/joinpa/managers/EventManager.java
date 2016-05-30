package io.joinpa.joinpa.managers;

import java.util.List;

import io.joinpa.joinpa.models.Event;

/**
 * Created by TAWEESOFT on 5/30/16 AD.
 */
public class EventManager {

    private List<Event> eventList;

    public EventManager(List<Event> eventList) {
        this.eventList = eventList;
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

    public List<Event> getEventList() {
        return eventList;
    }
}
