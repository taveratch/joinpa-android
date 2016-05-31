package io.joinpa.joinpa.ui.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Switch;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;

public class EventDetailsActivity extends AppCompatActivity {

    @BindView(R.id.sw_visibility)
    Switch swVisibility;

    @BindView(R.id.tv_choose_date)
    TextView tvChooseDate;

    @BindView(R.id.tv_choose_time)
    TextView tvChooseTime;

    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        ButterKnife.bind(this);
        initComponents();
    }

    public void initComponents() {

    }
}
