package io.joinpa.joinpa.ui.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Switch;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.App;
import io.joinpa.joinpa.models.Event;
import io.joinpa.joinpa.ui.adapters.LocationAdapter;

public class EventDetailsActivity extends AppCompatActivity {

    @BindView(R.id.tv_choose_date)
    TextView tvChooseDate;

    @BindView(R.id.tv_choose_time)
    TextView tvChooseTime;

    @BindView(R.id.rv)
    RecyclerView rv;

    private LocationAdapter locationAdapter;
    private App app;
    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        ButterKnife.bind(this);
        app = App.getInstance();
        event = (Event)getIntent().getSerializableExtra("event");
        initComponents();
    }

    public void initComponents() {
        locationAdapter = new LocationAdapter(this,app.getPlaceManager().getPlaces());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(locationAdapter);
    }

    @OnClick(R.id.tv_choose_date)
    public void openDatePicker() {

    }

    @OnClick(R.id.tv_choose_time)
    public void openTimePicker() {

    }

    @OnCheckedChanged(R.id.sw_visibility)
    public void setVisibility(boolean isChecked) {

    }
}
