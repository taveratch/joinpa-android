package io.joinpa.joinpa.ui.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.App;
import io.joinpa.joinpa.managers.DateTimeHolder;
import io.joinpa.joinpa.managers.EventManager;
import io.joinpa.joinpa.managers.commands.EditEventResponse;
import io.joinpa.joinpa.managers.commands.ObjectResponse;
import io.joinpa.joinpa.models.Event;
import io.joinpa.joinpa.models.Message;
import io.joinpa.joinpa.ui.dialogs.DateTimeSelectorDialog;
import io.joinpa.joinpa.util.DateUtil;
import io.joinpa.joinpa.util.ProgressDialogUtil;
import retrofit2.Response;

public class EventActivity extends AppCompatActivity implements Observer {

    @BindView(R.id.text_event_name)
    TextView eventName;

    @BindView(R.id.text_host_name)
    TextView hostName;

    @BindView(R.id.text_num_host_friends)
    TextView numHostFriends;

    @BindView(R.id.text_date)
    TextView eventDate;

    @BindView(R.id.text_time)
    TextView eventTime;

    @BindView(R.id.text_visibility)
    TextView visibility;

    @BindView(R.id.switch_visibility)
    Switch vSwitch;

    @BindView(R.id.text_location)
    TextView eventLocation;

    @BindView(R.id.btn_see_map)
    Button btnSeeMap;

    @BindView(R.id.btn_edit_name)
    Button btnEditName;

    @BindView(R.id.btn_edit_date)
    Button btnEditDate;

    @BindView(R.id.btn_confirm)
    ImageView btnConfirm;

    private Event event;
    private App app;
    private boolean isHost = false;
    private boolean nameEdited = false;
    private boolean dateEdited = false;
    private boolean visibilityEdited = false;
    private boolean placeEdited = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);
        app = App.getInstance();
        initComponents();
    }

    @OnClick(R.id.layout_coming_pane)
    public void seeComingPeople() {
        Intent intent = new Intent(this, WhoComingActivity.class);
        intent.putExtra("event", event);
        startActivity(intent);
    }

    @OnClick(R.id.btn_close)
    public void close() {
        finish();
    }

    @OnClick(R.id.btn_confirm)
    public void confirmEdit() {
        // TODO Send result to server

        Map<String, String> data = new HashMap<>();
        data.put("eventId", event.getId());
        if (nameEdited) data.put("name", event.getName());
        if (dateEdited) data.put("date", event.getDate().toLocaleString());
        if (visibilityEdited) data.put("isPrivate", event.isPrivate() + "");

        ProgressDialogUtil.show(this, "Please wait");
        EditEventResponse response = new EditEventResponse(data);
        response.addObserver(this);
        response.execute();
    }

    @OnClick(R.id.btn_see_map)
    public void showMap() {
        ProgressDialogUtil.show(this, "Opening map");
        Intent intent = new Intent(this,ShowMapActivity.class);
        intent.putExtra("place", event.getPlace());
        startActivity(intent);
    }

    @Override
    public void update(Observable observable, Object data) {
        if (data == null) return;
        if (!(data instanceof ObjectResponse)) return;

        ObjectResponse objectResponse = (ObjectResponse) data;

        if (objectResponse.isSuccess()) {
            Response<Message> response = (Response<Message>) objectResponse.getData();
            ProgressDialogUtil.dismiss();
            Toast.makeText(this, response.body().getMessage(), Toast.LENGTH_LONG).show();
            btnConfirm.setVisibility(View.INVISIBLE);

        } else {
            Log.e("error", objectResponse.getMessage());
            ProgressDialogUtil.dismiss();
        }
    }

    public void initComponents() {

        EventManager eventManager = app.getEventManager();
        int eventIndex = (int)getIntent().getSerializableExtra("event_index");
        event = eventManager.getTempEventList().get(eventIndex);

        if (event.getHost().equals(app.getUser())) isHost = true;

        if (isHost) {
            btnEditName.setVisibility(View.VISIBLE);
            btnEditDate.setVisibility(View.VISIBLE);
            vSwitch.setVisibility(View.VISIBLE);
        }
        Log.e("EventActivity", event.getName());

        Date date = event.getDate();

        eventName.setText(event.getName());
        hostName.setText(event.getHost().getUsername());
        numHostFriends.setText(event.getHost().getFriendList().size() + " friends");
        eventDate.setText(DateUtil.getDate(date));
        eventTime.setText(DateUtil.getTime(date));
        eventLocation.setText(event.getPlace().getName());

        if (event.isPrivate()) {
            visibility.setText("Hidden");
            vSwitch.setChecked(true);
        } else {
            visibility.setText("Shown to all");
            vSwitch.setChecked(false);
        }

        vSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    visibility.setText("Hidden");
                    event.setPrivate(true);

                } else {
                    visibility.setText("Shown to all");
                    event.setPrivate(false);
                }
                editVisibility();
            }
        });
        Log.e("isUseMap" , event.getPlace().isUseMap() + "");
        if (!event.getPlace().isUseMap()) btnSeeMap.setVisibility(View.GONE); //hide see map button
    }

    @OnClick(R.id.btn_invite)
    public void inviteFriend() {
        Intent intent = new Intent(this, InviteUserToEventActivity.class);
        intent.putExtra("event", event);
        startActivity(intent);
    }

    public void setChanged() {
        btnConfirm.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.text_event_name)
    public void editName() {
        nameEdited = true;
        setChanged();
        // TODO set text to new name
        // show dialog
        eventName.setText(event.getName());
    }

    @OnClick(R.id.btn_edit_date)
    public void editDate() {
        dateEdited = true;
        setChanged();
        DateTimeHolder dateTimeHolder = new DateTimeHolder(this, eventDate, eventTime);
        DateTimeSelectorDialog dialog = new DateTimeSelectorDialog(this, dateTimeHolder);
        dialog.show();
        event.setDate(dateTimeHolder.getDate());
    }

    public void editVisibility() {
        visibilityEdited = true;
        setChanged();
    }

    public void editPlace() {
        placeEdited = true;
        setChanged();
        eventLocation.setText(event.getPlace().getName());
    }
}
