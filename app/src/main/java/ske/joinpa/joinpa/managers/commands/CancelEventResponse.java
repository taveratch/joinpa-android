package ske.joinpa.joinpa.managers.commands;

import java.util.HashMap;
import java.util.Map;

import ske.joinpa.joinpa.managers.LoadService;

/**
 * Created by Peter on 6/1/2016 AD.
 */
public class CancelEventResponse extends ObjectResponse {

    private String eventId;

    public CancelEventResponse(String eventId) {
        this.eventId = eventId;
    }

    @Override
    public void execute() {
        LoadService loadService = LoadService.newInstance();
        Map<String,String> data = new HashMap<>();
        data.put("eventId", eventId);
        loadService.cancelEvent(data, this);
    }
}
