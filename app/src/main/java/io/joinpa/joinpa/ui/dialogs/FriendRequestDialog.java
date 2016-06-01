package io.joinpa.joinpa.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.List;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.commands.GetFriendListResponse;
import io.joinpa.joinpa.models.Friend;
import io.joinpa.joinpa.ui.adapters.FriendRequestAdapter;

/**
 * Created by TAWEESOFT on 5/27/16 AD.
 */
public class FriendRequestDialog extends Dialog {

    private List<Friend> friendRequest;
    private Observer observer;

    @BindView(R.id.rv)
    RecyclerView rv;

    @BindView(R.id.btn_close)
    Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_friend_request);
        ButterKnife.bind(this);
        initComponents();
    }

    public FriendRequestDialog(Context context, List<Friend> friendRequest,Observer observer) {
        super(context);
        this.friendRequest = friendRequest;
        this.observer = observer;
    }

    public void initComponents() {
        FriendRequestAdapter friendRequestAdapter = new FriendRequestAdapter(getContext(),friendRequest);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(friendRequestAdapter);
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                GetFriendListResponse getFriendListResponse = new GetFriendListResponse();
                getFriendListResponse.addObserver(observer); //UI that want to notify (FriendFragment)
                getFriendListResponse.execute();
            }
        });

    }

    @OnClick(R.id.btn_close)
    @Override
    public void dismiss() {
        super.dismiss();
    }
}
