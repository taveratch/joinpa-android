package io.joinpa.joinpa.managers.Commands;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import io.joinpa.joinpa.managers.LoadService;
import io.joinpa.joinpa.models.Element;
import retrofit2.Response;

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
        map.put("search" , search);
        loadService.searchFriend(map,this);
    }

}
