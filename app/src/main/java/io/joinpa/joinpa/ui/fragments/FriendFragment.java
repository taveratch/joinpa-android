package io.joinpa.joinpa.ui.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.App;
import io.joinpa.joinpa.managers.Commands.GetFriendListResponse;
import io.joinpa.joinpa.models.Friend;
import io.joinpa.joinpa.ui.adapters.FriendListAdapter;
import io.joinpa.joinpa.ui.adapters.FriendRequestAdapter;

/**
 * Created by TAWEESOFT on 5/15/16 AD.
 */
public class FriendFragment extends ObservableFragment implements Observer{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.et_search)
    EditText etSearch;

    @BindView(R.id.rv)
    RecyclerView rv;

    @BindView(R.id.layout_friend_request)
    FrameLayout layoutFriendRequest;

    @BindView(R.id.tv_friend_request_count)
    TextView tvFriendRequestCount;

    private App app;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friend_fragment_layout , container , false);
        ButterKnife.bind(this,view);
        app = App.getInstance();
        initComponents();
        return view;
    }

    public void initComponents() {
        List<Friend> friendList = app.getUser().getFriendList();
        final List<Friend> friendRequests = app.getUser().getFriendRequest();
        FriendListAdapter adapter = new FriendListAdapter(this.getContext(),friendList);
        adapter.setObserver(this);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setAdapter(adapter);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChanged();
                notifyObservers();
            }
        });
        if(friendRequests.size() == 0) layoutFriendRequest.setVisibility(View.GONE);
        tvFriendRequestCount.setText(friendRequests.size()+"");
        layoutFriendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFriendRequestDialog(friendRequests);
            }
        });
    }

    public void showFriendRequestDialog(List<Friend> friendRequests) {
        final Dialog dialog = new Dialog(FriendFragment.this.getContext());
        dialog.setContentView(R.layout.friend_request_list_layout);
        FriendRequestAdapter friendRequestAdapter = new FriendRequestAdapter(FriendFragment.this.getContext(),friendRequests);
        RecyclerView rv = ButterKnife.findById(dialog,R.id.rv);
        Button btn_close = ButterKnife.findById(dialog,R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(FriendFragment.this.getContext()));
        rv.setAdapter(friendRequestAdapter);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                GetFriendListResponse getFriendListResponse = new GetFriendListResponse();
                getFriendListResponse.addObserver(FriendFragment.this);
                getFriendListResponse.execute();
            }
        });
        dialog.show();
    }
    /**
     * Refresh friend list
     * @param observable
     * @param data
     */
    @Override
    public void update(java.util.Observable observable, Object data) {
        initComponents();
    }
}
