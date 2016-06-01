package io.joinpa.joinpa.managers.Commands;

import java.util.Observer;

import io.joinpa.joinpa.managers.Command;

/**
 * Created by TAWEESOFT on 6/1/16 AD.
 */
public class UnFriendCommand implements Command {

    private Observer observer;
    private String otherUserId;

    public UnFriendCommand(Observer observer, String otherUserId) {
        this.observer = observer;
        this.otherUserId = otherUserId;
    }

    @Override
    public void execute() {
        UnFriendResponse unFriendResponse = new UnFriendResponse(otherUserId);
        unFriendResponse.addObserver(observer);
        unFriendResponse.execute();
    }
}
