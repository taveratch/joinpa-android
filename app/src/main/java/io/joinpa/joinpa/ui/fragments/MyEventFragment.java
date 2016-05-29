package io.joinpa.joinpa.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.ui.views.NewEventActivity;

/**
 * Created by TAWEESOFT on 5/15/16 AD.
 */
public class MyEventFragment extends ObservableFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.img_create_new_event)
    ImageView imgCreateNewEvent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_event, container , false);
        ButterKnife.bind(this, view);
        initComponents();
        return view;
    }

    private void initComponents() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChanged();
                notifyObservers();
            }
        });
        imgCreateNewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NewEventActivity.class);
                startActivity(intent);
            }
        });
    }
}
