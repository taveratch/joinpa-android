package io.joinpa.joinpa.managers.commands;

import java.util.List;
import java.util.Observer;

import io.joinpa.joinpa.models.Event;
import io.joinpa.joinpa.ui.adapters.MyEventAdapter;
import io.joinpa.joinpa.ui.adapters.RecentEventAdapter;

/**
 * Created by TAWEESOFT on 6/1/16 AD.
 */
public class CancelEventCommand implements Command {

    private String eventId;
    private Observer observer;
    private RecentEventAdapter adapter;
    private int position;
    private List<Event> events;

    public CancelEventCommand(String eventId, Observer observer, RecentEventAdapter adapter, int position, List<Event> eventList) {
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
        CancelEventResponse response = new CancelEventResponse(eventId);
        response.addObserver(observer);
        response.execute();
    }
}
