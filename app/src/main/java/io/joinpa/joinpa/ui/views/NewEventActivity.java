package io.joinpa.joinpa.ui.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.App;
import io.joinpa.joinpa.managers.Constants;
import io.joinpa.joinpa.ui.adapters.CreateEventAdapter;

public class NewEventActivity extends AppCompatActivity implements Observer{

    @BindView(R.id.rv)
    RecyclerView rv;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

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
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        CreateEventAdapter adapter = new CreateEventAdapter(this, app.getInternalData().events);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    @Override
    public void update(Observable observable, Object data) {
        initComponents();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constants.EXIT) finish();
    }
}
