package io.joinpa.joinpa.ui.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import net.simonvt.menudrawer.MenuDrawer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.models.SideBarItem;
import io.joinpa.joinpa.ui.adapters.SideBarAdapter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lv_sidebar)
    ListView lvSideBar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

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
        List<SideBarItem> sideBarItems = new ArrayList<>();
        sideBarItems.add(new SideBarItem(getString(R.string.explore) , R.drawable.sidebar_explore_icon));
        sideBarItems.add(new SideBarItem(getString(R.string.my_events) , R.drawable.sidebar_myevent_icon));
        sideBarItems.add(new SideBarItem(getString(R.string.invites) , R.drawable.sidebar_invites_icon));
        sideBarItems.add(new SideBarItem(getString(R.string.recent_events) , R.drawable.sidebar_recent_icon));
        sideBarItems.add(new SideBarItem(getString(R.string.friends) , R.drawable.sidebar_friend_icon));

        SideBarAdapter sideBarAdapter = new SideBarAdapter(this,R.layout.sidebar_item_layout , sideBarItems);
        lvSideBar.setAdapter(sideBarAdapter);

        toolbar.setNavigationIcon(R.drawable.toolbar_menu_button);
        toolbar.setTitle("Main menu");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuDrawer.toggleMenu(true);
            }
        });

        // TODO: 5/15/16 AD Use fragment in Main activity 
    }
}
