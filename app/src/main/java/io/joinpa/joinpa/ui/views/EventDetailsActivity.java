package io.joinpa.joinpa.ui.views;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.App;
import io.joinpa.joinpa.managers.DateTimeHolder;
import io.joinpa.joinpa.managers.Notifier;
import io.joinpa.joinpa.models.Event;
import io.joinpa.joinpa.ui.adapters.LocationAdapter;

public class EventDetailsActivity extends AppCompatActivity implements Observer{

    @BindView(R.id.tv_choose_date)
    TextView tvChooseDate;

    @BindView(R.id.tv_choose_time)
    TextView tvChooseTime;

    @BindView(R.id.rv)
    RecyclerView rv;

    private LocationAdapter locationAdapter;
    private App app;
    private Event event;
    private DateTimeHolder dateTimeHolder;
    private Notifier notifier;

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
        initNotifier();
        dateTimeHolder = new DateTimeHolder(this,tvChooseDate,tvChooseTime);
        locationAdapter = new LocationAdapter(this,app.getPlaceManager().getPlaces(),notifier);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(locationAdapter);
    }

    @OnClick(R.id.tv_choose_date)
    public void openDatePicker() {
        dateTimeHolder.openDateDialog();
    }

    @OnClick(R.id.tv_choose_time)
    public void openTimePicker() {
        dateTimeHolder.openTimeDialog();
    }

    @OnCheckedChanged(R.id.sw_visibility)
    public void setVisibility(boolean isChecked) {
        if (isChecked)
            event.setPrivate(false);
        else
            event.setPrivate(true);
    }

    @Override
    public void update(Observable observable, Object data) {
        locationAdapter.notifyDataSetChanged();
    }

    public void initNotifier() {
        notifier = new Notifier();
        notifier.addObserver(this);
    }
}
