package io.joinpa.joinpa.ui.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.App;
import io.joinpa.joinpa.managers.EventManager;
import io.joinpa.joinpa.models.Event;
import io.joinpa.joinpa.util.DateUtil;

public class EventActivity extends AppCompatActivity {

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

    private Event event;
    private App app;

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

    @OnClick(R.id.btn_see_map)
    public void showMap() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);
        app = App.getInstance();
        EventManager eventManager = app.getEventManager();
        int eventIndex = (int)getIntent().getSerializableExtra("event_index");
        event = eventManager.getTempEventList().get(eventIndex);

        if (!event.getHost().equals(app.getUser())) vSwitch.setVisibility(View.INVISIBLE);

        Log.e("EventActivity", event.getName());

        Date date = event.getDate();

        eventName.setText(event.getName());
        hostName.setText(event.getHost().getUsername());
        // TODO tell nonae to send friendlist
//        numHostFriends.setText(event.getHost().getFriendList().size());
        eventDate.setText(DateUtil.getDate(date));
        eventTime.setText(DateUtil.getTime(date));
        eventLocation.setText(event.getPlace().getName());
        if (!event.isPrivate()) vSwitch.setChecked(false);

        vSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    visibility.setText("Hidden");
                } else {
                    visibility.setText("Public");
                }
            }
        });

    }



}
