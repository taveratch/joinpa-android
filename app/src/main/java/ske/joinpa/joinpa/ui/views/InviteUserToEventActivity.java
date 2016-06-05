package ske.joinpa.joinpa.ui.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ske.joinpa.joinpa.R;
import ske.joinpa.joinpa.managers.App;
import ske.joinpa.joinpa.managers.Notifier;
import ske.joinpa.joinpa.managers.commands.InviteFriendResponse;
import ske.joinpa.joinpa.managers.commands.ObjectResponse;
import ske.joinpa.joinpa.models.Event;
import ske.joinpa.joinpa.models.Friend;
import ske.joinpa.joinpa.models.Message;
import ske.joinpa.joinpa.ui.adapters.SelectFriendAdapter;
import ske.joinpa.joinpa.util.DialogMessageUtil;
import ske.joinpa.joinpa.util.ProgressDialogUtil;
import retrofit2.Response;

public class InviteUserToEventActivity extends AppCompatActivity implements Observer{

    @BindView(R.id.rv)
    RecyclerView rv;

    @BindView(R.id.et_search)
    EditText etSearch;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_select_friend_ok)
    TextView tvOk;

    private SelectFriendAdapter adapter;
    private int selectedCount=0;
    private App app;
    private Notifier notifier;
    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_user_to_event);
        ButterKnife.bind(this);
        app = App.getInstance();
        event = (Event)getIntent().getSerializableExtra("event");
        initComponents();
    }

    public void initComponents() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        notifier = new Notifier();
        notifier.addObserver(this);
        List<Friend> friendList = app.getUser().getFriendList();
        adapter = new SelectFriendAdapter(this,friendList,notifier);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    @Override
    public void update(Observable observable, Object data) {
        if (data == null) return;
        if (data instanceof ObjectResponse){ //from InviteFriendResponse
            ObjectResponse objectResponse = (ObjectResponse)data;
            if(objectResponse.isSuccess()){
                Response<Message> response = (Response<Message>)objectResponse.getData();
                Message message = response.body();
                DialogMessageUtil.showToast(this,message.getMessage());
                finish();
            }else{
                System.out.println("error " + objectResponse.getMessage() );
            }
        }else{
            boolean isIncrease = (boolean)data;
            if (isIncrease) selectedCount++;
            else selectedCount--;
            tvOk.setText(String.format("OK (%d)" , selectedCount));
        }

    }

    @OnClick(R.id.tv_select_friend_ok)
    public void invite() {
        List<Friend> selectedFriend = adapter.getSelectedFriends();
        ProgressDialogUtil.show(this,String.format("Inviting %d friends",selectedFriend.size()));
        InviteFriendResponse response = new InviteFriendResponse(selectedFriend,event.getId());
        response.addObserver(this);
        response.execute();
    }
}
