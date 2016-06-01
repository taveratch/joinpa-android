package io.joinpa.joinpa.managers.commands;

import java.util.List;
import java.util.Observer;

import io.joinpa.joinpa.models.Event;
import io.joinpa.joinpa.ui.adapters.MyEventAdapter;

/**
 * Created by TAWEESOFT on 6/1/16 AD.
 */
public class RemoveEventCommand implements Command{

    private String eventId;
    private Observer observer;
    private MyEventAdapter adapter;
    private int position;
    private List<Event> events;

    public RemoveEventCommand(String eventId, Observer observer, MyEventAdapter adapter, int position, List<Event> eventList) {
        this.eventId = eventId;
        this.observer = observer;
        this.adapter = adapter;
        this.position = position;
        this.events = eventList;
    }

    @Override
    public void execute() {
        events.remove(position);
        adapter.notifyItemRemoved(position);
        RemoveEventResponse response = new RemoveEventResponse(eventId);
        response.addObserver(observer);
        response.execute();
    }
}
