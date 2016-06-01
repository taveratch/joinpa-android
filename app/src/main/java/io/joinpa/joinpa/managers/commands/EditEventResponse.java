package io.joinpa.joinpa.managers.commands;

import java.util.Map;

import io.joinpa.joinpa.managers.LoadService;

/**
 * Created by Peter on 6/1/2016 AD.
 */
public class EditEventResponse extends ObjectResponse {

    Map<String, String> data;

    public EditEventResponse(Map<String, String> data) {
        this.data = data;
    }

    @Override
    public void execute() {
        LoadService loadService = LoadService.newInstance();
        loadService.editEvent(data, this);
    }
}
