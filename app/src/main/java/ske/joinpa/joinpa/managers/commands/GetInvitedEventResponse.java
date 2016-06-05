package ske.joinpa.joinpa.managers.commands;

import ske.joinpa.joinpa.managers.LoadService;

/**
 * Created by Peter on 6/1/2016 AD.
 */
public class GetInvitedEventResponse extends ObjectResponse {
    @Override
    public void execute() {
        LoadService loadService = LoadService.newInstance();
        loadService.getInvitedEvents(this);
    }
}
