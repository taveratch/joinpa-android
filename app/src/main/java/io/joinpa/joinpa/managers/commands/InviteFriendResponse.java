package io.joinpa.joinpa.managers.commands;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.joinpa.joinpa.managers.LoadService;
import io.joinpa.joinpa.models.Friend;

/**
 * Created by TAWEESOFT on 6/1/16 AD.
 */
public class InviteFriendResponse extends ObjectResponse {

    private List<Friend> selectedFriend;
    private String eventId;

    public InviteFriendResponse(List<Friend> selectedFriend, String eventId) {
        this.selectedFriend = selectedFriend;
        this.eventId = eventId;
    }

    @Override
    public void execute() {
        LoadService loadService = LoadService.newInstance();
        Gson gson = new Gson();
        Type type = new TypeToken<List<Friend>>(){}.getType();
        Map<String,String> data = new HashMap<>();
        data.put("eventId" , eventId);
        data.put("invitedList" , gson.toJson(selectedFriend,type));
        loadService.inviteFriend(data, this);
    }
}
