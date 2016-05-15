package io.joinpa.joinpa.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;

/**
 * Created by TAWEESOFT on 5/15/16 AD.
 */
public class ExploreFragment extends ObservableFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.explore_fragment_layout, container, false);
        ButterKnife.bind(this,view);
        initComponents();
        return view;
    }

    public void initComponents() {
        toolbar.setNavigationIcon(R.drawable.toolbar_menu_button);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChanged();
                notifyObservers();
            }
        });
    }
}
