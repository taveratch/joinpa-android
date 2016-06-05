package ske.joinpa.joinpa.managers.commands;

import java.util.HashMap;
import java.util.Map;

import ske.joinpa.joinpa.managers.LoadService;

/**
 * Created by Peter on 5/31/2016 AD.
 */
public class JoinEventResponse extends ObjectResponse {

    private String eventId;

    public JoinEventResponse(String eventId) {
        this.eventId = eventId;
    }

    @Override
    public void execute() {
        LoadService loadService = LoadService.newInstance();
        Map<String,String> data = new HashMap<>();
        data.put("eventId", eventId);
        loadService.joinEvent(data, this);
    }
}
