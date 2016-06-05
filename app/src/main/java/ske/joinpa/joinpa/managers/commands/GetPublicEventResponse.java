package ske.joinpa.joinpa.managers.commands;

import ske.joinpa.joinpa.managers.LoadService;


/**
 * Created by Peter on 5/30/2016 AD.
 */
public class GetPublicEventResponse extends ObjectResponse {

    @Override
    public void execute() {
        LoadService loadService = LoadService.newInstance();
        loadService.getPublicEvents(this);
    }

}
