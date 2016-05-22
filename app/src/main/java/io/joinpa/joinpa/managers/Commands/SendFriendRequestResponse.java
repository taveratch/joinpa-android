package io.joinpa.joinpa.managers.Commands;

import java.util.HashMap;
import java.util.Map;

import io.joinpa.joinpa.managers.LoadService;
/**
 * Created by TAWEESOFT on 5/21/16 AD.
 */
public class SendFriendRequestResponse extends ObjectResponse {

    private String otherUserId;

    public SendFriendRequestResponse(String otherUserId) {
        this.otherUserId = otherUserId;
    }

    @Override
    public void execute() {
        LoadService loadService = LoadService.newInstance();
        Map<String,String> data = new HashMap<>();
        data.put("otherUserId" , otherUserId);
        loadService.sendFriendRequest(data , this);
    }

}
