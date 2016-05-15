package io.joinpa.joinpa.ui.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.simonvt.menudrawer.MenuDrawer;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.models.SideBarItem;

public class MainActivity extends AppCompatActivity {


    private MenuDrawer menuDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menuDrawer = MenuDrawer.attach(this);
        menuDrawer.setContentView(R.layout.activity_main);
        menuDrawer.setMenuView(R.layout.sidebar_layout);
        ButterKnife.bind(menuDrawer);
        ButterKnife.bind(this);
        initComponents();
    }

    public void initComponents() {
    }
}
