package io.joinpa.joinpa.managers.commands;

import java.util.List;
import java.util.Observable;

import io.joinpa.joinpa.managers.App;
import io.joinpa.joinpa.managers.LoadService;
import io.joinpa.joinpa.models.Element;
import io.joinpa.joinpa.models.Friend;
import retrofit2.Response;

/**
 * Created by TAWEESOFT on 5/21/16 AD.
 */
public class GetFriendListResponse extends ObjectResponse {

    private App app;

    public GetFriendListResponse() {
        app = App.getInstance();
    }

    @Override
    public void execute() {
        LoadService loadService = LoadService.newInstance();
        loadService.getFriendList(this);
    }

    @Override
    public void update(Observable observable, Object data) {
        if( data == null ) return;
        Response<Element> response = (Response<Element>)data;
        List<Friend> friends = response.body().getFriendList();
        app.refreshFriendList(friends);
        super.update(observable, data);
    }
}
