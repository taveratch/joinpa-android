package io.joinpa.joinpa.ui.views;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import net.simonvt.menudrawer.MenuDrawer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.models.SideBarItem;
import io.joinpa.joinpa.ui.adapters.SideBarAdapter;
import io.joinpa.joinpa.ui.fragments.ExploreFragment;

public class MainActivity extends AppCompatActivity implements Observer {

    @BindView(R.id.lv_sidebar)
    ListView lvSideBar;

    private MenuDrawer menuDrawer;
    private ExploreFragment exploreFragment;

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
        //Create explore fragment and add this as observer to toggle left menu drawer.
        exploreFragment = new ExploreFragment();
        exploreFragment.addObserver(this);

        List<SideBarItem> sideBarItems = new ArrayList<>();
        sideBarItems.add(new SideBarItem(getString(R.string.explore) , R.drawable.sidebar_explore_icon));
        sideBarItems.add(new SideBarItem(getString(R.string.my_events) , R.drawable.sidebar_myevent_icon));
        sideBarItems.add(new SideBarItem(getString(R.string.invites) , R.drawable.sidebar_invites_icon));
        sideBarItems.add(new SideBarItem(getString(R.string.recent_events) , R.drawable.sidebar_recent_icon));
        sideBarItems.add(new SideBarItem(getString(R.string.friends) , R.drawable.sidebar_friend_icon));

        SideBarAdapter sideBarAdapter = new SideBarAdapter(this,R.layout.sidebar_item_layout , sideBarItems);
        lvSideBar.setAdapter(sideBarAdapter);

        switchFragment(0); //Show primary fragment which is Explore
    }

    public void replaceFragment(Fragment fragmnet) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framel_fragments, fragmnet)
                .addToBackStack(null)
                .commit();
    }

    public void switchFragment(int id) {
        // TODO: 5/15/16 AD Add more fragment
        switch (id) {
            case 0 :
                replaceFragment(exploreFragment);
                break;
        }
    }

    /**
     * To toggle menu drawer from another fragment
     * @param observable
     * @param o
     */
    @Override
    public void update(Observable observable, Object o) {
        menuDrawer.toggleMenu(true);
    }
}
