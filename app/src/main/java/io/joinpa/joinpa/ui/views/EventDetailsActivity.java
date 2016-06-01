package io.joinpa.joinpa.ui.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.App;
import io.joinpa.joinpa.managers.commands.CreateEventResponse;
import io.joinpa.joinpa.managers.commands.ObjectResponse;
import io.joinpa.joinpa.managers.Constants;
import io.joinpa.joinpa.managers.DateTimeHolder;
import io.joinpa.joinpa.managers.Notifier;
import io.joinpa.joinpa.models.Event;
import io.joinpa.joinpa.models.Message;
import io.joinpa.joinpa.models.Place;
import io.joinpa.joinpa.ui.adapters.LocationAdapter;
import io.joinpa.joinpa.util.DialogMessageUtil;
import retrofit2.Response;

public class EventDetailsActivity extends AppCompatActivity implements Observer {

    @BindView(R.id.tv_choose_date)
    TextView tvChooseDate;

    @BindView(R.id.tv_choose_time)
    TextView tvChooseTime;

    @BindView(R.id.rv)
    RecyclerView rv;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_visibility)
    TextView tvVisibility;

    @BindView(R.id.tv_visibility_desc)
    TextView tvVisibilityDesc;

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

    @Override
    public void update(Observable observable, Object data) {
        locationAdapter.notifyDataSetChanged();
        if (data == null) return;
        if (!(data instanceof ObjectResponse)) return;
        ObjectResponse objectResponse = (ObjectResponse)data;
        if (objectResponse.isSuccess()){
            Response<Message> response = (Response<Message>)objectResponse.getData();
            Message message = response.body();
            Toast.makeText(this, message.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("success" , message.getMessage()+"");
        } else {
            Log.e("failed" , objectResponse.getMessage()+"");
            Toast.makeText(this, objectResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
        setResult(Constants.EXIT);
        finish();
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
        if (isChecked){
            event.setPrivate(true);
            tvVisibility.setText(getString(R.string.hidden));
            tvVisibilityDesc.setText(getString(R.string.hidden_desc));
        }
        else{
            event.setPrivate(false);
            tvVisibility.setText(getString(R.string.shown_to_all));
            tvVisibilityDesc.setText(getString(R.string.public_desc));
        }

    }

    @OnClick(R.id.img_ok)
    public void save() {
        Place place = locationAdapter.getSelectedPlace();
        if(place == null){
            DialogMessageUtil.showToast(this,getString(R.string.please_select_location));
            return;
        }
        event.setPlace(place);
        event.setTimeStamp(System.currentTimeMillis());
        Date date = dateTimeHolder.getDate();
        event.setDate(date);
        Gson gson = new Gson();
        System.out.println(gson.toJson(event));
        CreateEventResponse response = new CreateEventResponse(event);
        response.addObserver(this);
        response.execute();
    }

    public void initNotifier() {
        notifier = new Notifier();
        notifier.addObserver(this);
    }

    public void initComponents() {
        initNotifier();
        dateTimeHolder = new DateTimeHolder(this, tvChooseDate, tvChooseTime);
        locationAdapter = new LocationAdapter(this,app.getPlaceManager().getPlaces(), notifier);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(locationAdapter);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



}
