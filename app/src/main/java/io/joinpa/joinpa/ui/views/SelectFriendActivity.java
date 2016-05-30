package io.joinpa.joinpa.ui.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.App;
import io.joinpa.joinpa.managers.Notifier;
import io.joinpa.joinpa.models.Event;
import io.joinpa.joinpa.models.Friend;
import io.joinpa.joinpa.ui.adapters.SelectFriendAdapter;

public class SelectFriendActivity extends AppCompatActivity implements Observer {

    @BindView(R.id.rv)
    RecyclerView rv;

    @BindView(R.id.et_search)
    EditText etSearch;

    @BindView(R.id.tv_select_friend_ok)
    TextView tvOk;

    private SelectFriendAdapter adapter;
    private Event event;
    private Notifier notifier;
    private App app;
    private int selectedCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_friend);
        ButterKnife.bind(this);
        event = (Event)getIntent().getSerializableExtra("event");
        app = App.getInstance();
        initComponents();
    }

    public void initComponents() {
        notifier = new Notifier();
        notifier.addObserver(this);
        adapter = new SelectFriendAdapter(this,app.getUser().getFriendList(),notifier);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    @OnClick(R.id.tv_select_friend_ok)
    public void next() {
        List<Friend> selectedFriend = adapter.getSelectedFriends();
        event.setPendingList(selectedFriend);
        // TODO: 5/30/16 AD send event to EventDetailsActivity 
    }

    @Override
    public void update(Observable observable, Object data) {
        if (data == null) return;
        boolean isIncrease = (boolean)data;
        if (isIncrease) selectedCount++;
        else selectedCount--;
        tvOk.setText(String.format("OK (%d)" , selectedCount));
    }
}
