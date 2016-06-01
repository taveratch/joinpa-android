package io.joinpa.joinpa.managers.commands;

import java.util.HashMap;
import java.util.Map;

import io.joinpa.joinpa.managers.LoadService;

/**
 * Created by TAWEESOFT on 5/22/16 AD.
 */
public class UnFriendResponse extends ObjectResponse {

    private String otherUserId;

    public UnFriendResponse(String otherUserId) {
        this.otherUserId = otherUserId;
    }

    @Override
    public void execute() {
        LoadService loadService = LoadService.newInstance();
        Map<String,String> data = new HashMap<>();
        data.put("otherUserId", otherUserId);
        loadService.unfriend(data, this);
    }
}
