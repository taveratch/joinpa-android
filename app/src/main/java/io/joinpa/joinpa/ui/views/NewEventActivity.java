package io.joinpa.joinpa.ui.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.App;
import io.joinpa.joinpa.ui.adapters.CreateEventAdapter;

public class NewEventActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView rv;

    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        ButterKnife.bind(this);
        app = App.getInstance();
        initComponents();
    }

    public void initComponents() {
        CreateEventAdapter adapter = new CreateEventAdapter(this, app.getInternalData().events);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }
}
