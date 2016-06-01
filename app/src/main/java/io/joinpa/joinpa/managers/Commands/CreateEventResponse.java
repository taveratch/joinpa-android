package io.joinpa.joinpa.managers.commands;

import io.joinpa.joinpa.managers.LoadService;
import io.joinpa.joinpa.models.Event;

/**
 * Created by TAWEESOFT on 5/31/16 AD.
 */
public class CreateEventResponse extends ObjectResponse {

    private Event event;

    public CreateEventResponse(Event event) {
        this.event = event;
    }

    @Override
    public void execute() {
        LoadService loadService = LoadService.newInstance();
        loadService.createEvent(event, this);
    }
}
