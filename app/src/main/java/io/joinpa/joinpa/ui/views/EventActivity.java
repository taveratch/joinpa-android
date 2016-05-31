package io.joinpa.joinpa.ui.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;

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

    @BindView(R.id.btn_close)
    Button closeBtn;

    @BindView(R.id.btn_see_map)
    Button seeMapBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);
    }
}
