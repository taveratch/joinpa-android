package io.joinpa.joinpa.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;
<<<<<<< 340dad269d95eab42a45bc9f35cfe24b6d1e0283
import io.joinpa.joinpa.managers.App;
=======
>>>>>>> [#119977243] Added friend list layout with fake data
import io.joinpa.joinpa.models.Friend;
import io.joinpa.joinpa.ui.adapters.FriendListAdapter;

/**
 * Created by TAWEESOFT on 5/15/16 AD.
 */
public class FriendFragment extends ObservableFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.et_search)
    EditText etSearch;

    @BindView(R.id.rv)
    RecyclerView rv;

<<<<<<< 340dad269d95eab42a45bc9f35cfe24b6d1e0283
    private App app;
=======
>>>>>>> [#119977243] Added friend list layout with fake data
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
<<<<<<< 340dad269d95eab42a45bc9f35cfe24b6d1e0283
        List<Friend> friendList = app.getUser().getFriendList();
=======

        List<Friend> friendList = new ArrayList<>();
        friendList.add(null);
        Friend f1 = new Friend();
        f1.setUsername("tonyslark");
        Friend f2 = new Friend();
        f2.setUsername("jason.b");
        Friend f3 = new Friend();
        f3.setUsername("freddy.c");
        friendList.add(f1);
        friendList.add(f2);
        friendList.add(f3);
>>>>>>> [#119977243] Added friend list layout with fake data
        FriendListAdapter adapter = new FriendListAdapter(this.getContext(),friendList);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setAdapter(adapter);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChanged();
                notifyObservers();
            }
        });
    }

}
