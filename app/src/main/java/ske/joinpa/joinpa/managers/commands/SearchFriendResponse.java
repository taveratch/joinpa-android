package ske.joinpa.joinpa.managers.commands;

import java.util.HashMap;
import java.util.Map;

import ske.joinpa.joinpa.managers.LoadService;

/**
 * Created by TAWEESOFT on 5/21/16 AD.
 */
public class SearchFriendResponse extends ObjectResponse {

    private String search;

    public SearchFriendResponse(String search) {
        this.search = search;
    }

    @Override
    public void execute() {
        LoadService loadService = LoadService.newInstance();
        Map<String,String> map = new HashMap<>();
        map.put("search", search);
        loadService.searchFriend(map, this);
    }

}
